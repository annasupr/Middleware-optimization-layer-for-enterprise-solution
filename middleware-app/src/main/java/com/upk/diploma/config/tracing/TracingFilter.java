package com.upk.diploma.config.tracing;

import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanBuilder;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.propagation.SpanContextParseException;
import io.opencensus.trace.propagation.TextFormat;
import io.opencensus.trace.samplers.Samplers;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class TracingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(TracingFilter.class);
    private static final Tracer tracer = Tracing.getTracer();
    private static final TextFormat textFormat = Tracing.getPropagationComponent().getB3Format();
    private static final TextFormat.Getter<Map<String, String>> getter = new TextFormat.Getter<>() {
        @Override
        public String get(Map<String, String> httpHeaders, String s) {
            return httpHeaders.get(s);
        }
    };

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            SpanContext spanContext;
            SpanBuilder spanBuilder;

            String spanName = request.getMethod() + " " + request.getRequestURI();

            Map<String, String> headers = Collections.list(request.getHeaderNames())
                    .stream()
                    .collect(Collectors.toMap(h -> h, request::getHeader));

            try {
                spanContext = textFormat.extract(headers, getter);
                spanBuilder = tracer.spanBuilderWithRemoteParent(spanName, spanContext);
            } catch (SpanContextParseException e) {
                spanBuilder = tracer.spanBuilder(spanName);
                logger.warn("Parent Span is not present");
            }

            Span span = spanBuilder.setRecordEvents(true)
                    .setSampler(Samplers.alwaysSample()).startSpan();

            try (Scope s = tracer.withSpan(span)) {
                filterChain.doFilter(request, response);
            }

            span.end();
        }
    }
