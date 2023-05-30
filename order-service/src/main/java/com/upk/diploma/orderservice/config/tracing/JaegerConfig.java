package com.upk.diploma.orderservice.config.tracing;

import io.opencensus.exporter.trace.jaeger.JaegerTraceExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerConfig {

    public JaegerConfig(@Value("${opentracing.jaeger.http-sender.url}") String jaegerThriftEndpoint) {
        JaegerTraceExporter.createAndRegister(jaegerThriftEndpoint, "order-service");
    }

    @Bean
    public FilterRegistrationBean tracingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TracingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
