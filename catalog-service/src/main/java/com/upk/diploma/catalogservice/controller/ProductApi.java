package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.service.ProductService;
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
@RequestMapping(ProductApi.PRODUCTS_API_PATH)
@RequiredArgsConstructor
public class ProductApi {
    public static final String PRODUCTS_API_PATH = "/api/products";

    private final ProductService productService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable final Long productId) {
        Span span = SpanUtils.buildSpan(tracer, "ProductApi getProductById").startSpan();
        ProductResponse foundProduct = null;
        try (Scope ws = tracer.withSpan(span)) {
            foundProduct = productService.getById(productId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        Span span = SpanUtils.buildSpan(tracer, "ProductApi getAllProducts").startSpan();
        List<ProductResponse> allProducts = null;
        try (Scope ws = tracer.withSpan(span)) {
            allProducts = productService.getAll();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody final ProductResponse productCreateRequest) {
        Span span = SpanUtils.buildSpan(tracer, "ProductApi createProduct").startSpan();
        ProductResponse createdProduct = null;
        try (Scope ws = tracer.withSpan(span)) {
            createdProduct = productService.create(productCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable final Long productId,
            @RequestBody final ProductResponse productUpdateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "ProductApi updateProduct").startSpan();
        ProductResponse updatedProduct = null;
        try (Scope ws = tracer.withSpan(span)) {
            updatedProduct = productService.update(productId, productUpdateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long productId) {
        Span span = SpanUtils.buildSpan(tracer, "ProductApi deleteProduct").startSpan();
        try (Scope ws = tracer.withSpan(span)) {
            productService.delete(productId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
