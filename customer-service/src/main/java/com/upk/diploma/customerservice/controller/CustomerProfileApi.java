package com.upk.diploma.customerservice.controller;

import com.upk.diploma.customerservice.dto.CustomerProfileResponse;
import com.upk.diploma.customerservice.service.CustomerProfileService;
import com.upk.diploma.customerservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerProfileApi.CUSTOMER_PROFILE_API_PATH)
@RequiredArgsConstructor
public class CustomerProfileApi {
    public static final String CUSTOMER_PROFILE_API_PATH = "/api/customer-profile";

    private final CustomerProfileService customerProfileService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/{customerProfileId}")
    public ResponseEntity<CustomerProfileResponse> getCustomerProfileById(@PathVariable final Long customerProfileId) {
        Span span = SpanUtils.buildSpan(tracer, "CustomerProfileApi getCustomerProfileById").startSpan();
        CustomerProfileResponse customerProfile = null;
        try (Scope ws = tracer.withSpan(span)) {
            customerProfile = customerProfileService.getById(customerProfileId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(customerProfile, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerProfileResponse> createCustomerProfile(
            @RequestBody final CustomerProfileResponse customerProfileCreateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "CustomerProfileApi createCustomerProfile").startSpan();
        CustomerProfileResponse createdCustomerProfile = null;
        try (Scope ws = tracer.withSpan(span)) {
            createdCustomerProfile = customerProfileService.create(customerProfileCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(createdCustomerProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{customerProfileId}")
    public ResponseEntity<CustomerProfileResponse> updateCustomerProfile(
            @PathVariable final Long customerProfileId,
            @RequestBody final CustomerProfileResponse customerProfileCreateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "CustomerProfileApi updateCustomerProfile").startSpan();
        CustomerProfileResponse updatedCustomerProfile = null;
        try (Scope ws = tracer.withSpan(span)) {
            updatedCustomerProfile = customerProfileService.update(customerProfileId, customerProfileCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(updatedCustomerProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{customerProfileId}")
    public ResponseEntity<Void> deleteCustomerProfile(@PathVariable final Long customerProfileId) {
        Span span = SpanUtils.buildSpan(tracer, "CustomerProfileApi deleteCustomerProfile").startSpan();
        try (Scope ws = tracer.withSpan(span)) {
            customerProfileService.delete(customerProfileId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
