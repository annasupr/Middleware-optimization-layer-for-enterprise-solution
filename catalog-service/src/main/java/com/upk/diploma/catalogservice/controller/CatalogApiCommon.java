package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.DurationResponse;
import com.upk.diploma.catalogservice.dto.MarketResponse;
import com.upk.diploma.catalogservice.dto.PointOfDistributionResponse;
import com.upk.diploma.catalogservice.dto.ProductCategoryResponse;
import com.upk.diploma.catalogservice.dto.StorehouseResponse;
import com.upk.diploma.catalogservice.service.CatalogServiceCommon;
import com.upk.diploma.catalogservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CatalogApiCommon.CATALOG_API_PATH)
@RequiredArgsConstructor
public class CatalogApiCommon {
    public static final String CATALOG_API_PATH = "/api/catalog";

    private final CatalogServiceCommon catalogService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/storehouses")
    public ResponseEntity<List<StorehouseResponse>> getAllStorehouses() {
        Span span = SpanUtils.buildSpan(tracer, "CatalogApiCommon getAllStorehouses").startSpan();
        List<StorehouseResponse> storehouses = null;
        try (Scope ws = tracer.withSpan(span)) {
            storehouses = catalogService.getAllStorehouses();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(storehouses, HttpStatus.OK);
    }

    @GetMapping("/markets")
    public ResponseEntity<List<MarketResponse>> getAllMarkets() {
        Span span = SpanUtils.buildSpan(tracer, "CatalogApiCommon getAllMarkets").startSpan();
        List<MarketResponse> markets = null;
        try (Scope ws = tracer.withSpan(span)) {
            markets = catalogService.getAllMarkets();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(markets, HttpStatus.OK);
    }

    @GetMapping("/points-of-distribution")
    public ResponseEntity<List<PointOfDistributionResponse>> getAllPointsOfDistribution() {
        Span span = SpanUtils.buildSpan(tracer, "CatalogApiCommon getAllPointsOfDistribution").startSpan();
        List<PointOfDistributionResponse> pointsOfDistribution = null;
        try (Scope ws = tracer.withSpan(span)) {
            pointsOfDistribution = catalogService.getAllPointsOfDistribution();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(pointsOfDistribution, HttpStatus.OK);
    }

    @GetMapping("/durations")
    public ResponseEntity<List<DurationResponse>> getAllDurations() {
        Span span = SpanUtils.buildSpan(tracer, "CatalogApiCommon getAllDurations").startSpan();
        List<DurationResponse> durations = null;
        try (Scope ws = tracer.withSpan(span)) {
            durations = catalogService.getAllDurations();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(durations, HttpStatus.OK);
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories() {
        Span span = SpanUtils.buildSpan(tracer, "CatalogApiCommon getAllProductCategories").startSpan();
        List<ProductCategoryResponse> productCategories = null;
        try (Scope ws = tracer.withSpan(span)) {
            productCategories = catalogService.getAllProductCategories();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }
}
