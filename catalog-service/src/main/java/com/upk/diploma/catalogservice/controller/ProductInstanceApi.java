package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.service.ProductInstanceService;
import com.upk.diploma.catalogservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductInstanceApi.PRODUCT_INSTANCES_API_PATH)
@RequiredArgsConstructor
public class ProductInstanceApi {
    public static final String PRODUCT_INSTANCES_API_PATH = "/api/product-instances";

    private final ProductInstanceService productInstanceService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/{productInstanceId}")
    public ResponseEntity<ProductInstanceResponse> getProductInstanceById(
            @PathVariable final Long productInstanceId) {
        Span span = SpanUtils.buildSpan(tracer, "ProductInstanceApi getProductInstanceById").startSpan();
        ProductInstanceResponse foundProductInstance = null;
        try (Scope ws = tracer.withSpan(span)) {
            foundProductInstance = productInstanceService.getById(productInstanceId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(foundProductInstance, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductInstanceResponse>> getAllProductInstances() {
        Span span = SpanUtils.buildSpan(tracer, "ProductInstanceApi getAllProductInstances").startSpan();
        List<ProductInstanceResponse> allProductInstances = null;
        try (Scope ws = tracer.withSpan(span)) {
            allProductInstances = productInstanceService.getAll();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(allProductInstances, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductInstanceResponse> createProductInstance(
            @RequestBody final ProductInstanceResponse productInstanceCreateRequest) {
        Span span = SpanUtils.buildSpan(tracer, "ProductInstanceApi createProductInstance").startSpan();
        ProductInstanceResponse createdProductInstance = null;
        try (Scope ws = tracer.withSpan(span)) {
            createdProductInstance = productInstanceService.create(productInstanceCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(createdProductInstance, HttpStatus.CREATED);
    }

    @PutMapping("/{productInstanceId}")
    public ResponseEntity<ProductInstanceResponse> updateProductInstance(
            @PathVariable final Long productInstanceId,
            @RequestBody final ProductInstanceResponse productInstanceUpdateRequest) {
        Span span = SpanUtils.buildSpan(tracer, "ProductInstanceApi updateProductInstance").startSpan();
        ProductInstanceResponse updatedProductInstance = null;
        try (Scope ws = tracer.withSpan(span)) {
            updatedProductInstance = productInstanceService.update(productInstanceId, productInstanceUpdateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(updatedProductInstance, HttpStatus.OK);
    }

    @DeleteMapping("/{productInstanceId}")
    public ResponseEntity<Void> deleteProductInstance(@PathVariable final Long productInstanceId) {
        Span span = SpanUtils.buildSpan(tracer, "ProductInstanceApi deleteProductInstance").startSpan();
        try (Scope ws = tracer.withSpan(span)) {
            productInstanceService.delete(productInstanceId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
