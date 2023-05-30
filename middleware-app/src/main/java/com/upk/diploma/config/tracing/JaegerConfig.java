package com.upk.diploma.config.tracing;

import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerConfig {

    public JaegerConfig(@Value("${opentracing.jaeger.http-sender.url}") String jaegerThriftEndpoint) {
        JaegerTraceExporter.createAndRegister(jaegerThriftEndpoint, "middleware-service");
    }
}
