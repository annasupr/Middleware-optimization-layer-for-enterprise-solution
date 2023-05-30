package com.upk.diploma.config.tracing;

import com.upk.diploma.util.SpanUtils;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import io.opencensus.trace.propagation.TextFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateTracingInterceptor implements ClientHttpRequestInterceptor {

    private static final Tracer tracer = Tracing.getTracer();

    private static final TextFormat textFormat = Tracing.getPropagationComponent().getB3Format();
    private static final TextFormat.Setter<HttpHeaders> setter = new TextFormat.Setter<>() {
        public void put(HttpHeaders carrier, String key, String value) {
            carrier.add(key, value);
        }
    };

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String spanName = request.getMethod() + " " + request.getURI();
        Span span = SpanUtils.buildSpan(tracer, "Middleware api request: " + spanName)
                .startSpan();

        textFormat.inject(span.getContext(), request.getHeaders(), setter);
        ClientHttpResponse response = execution.execute(request, body);

        span.end();

        return response;
    }
}
