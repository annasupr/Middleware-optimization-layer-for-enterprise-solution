package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.config.properties.PaginationProperties;
import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.dto.SearchResponse;
import com.upk.diploma.catalogservice.service.SearchService;
import com.upk.diploma.catalogservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(SearchApi.SEARCH_PUBLIC_API_PATH)
@RequiredArgsConstructor
public class SearchApi {

    private final SearchService searchService;
    private final PaginationProperties paginationProperties;
    public static final String SEARCH_PUBLIC_API_PATH = "/api/public/search";

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/offers")
    public ResponseEntity<SearchResponse<OfferResponse>> searchOffers(
            @RequestParam(value = "text") final String text,
            @RequestHeader(value = "page", required = false) Integer page,
            @RequestHeader(value = "pageSize", required = false) Integer pageSize
    ) {
        Span span = SpanUtils.buildSpan(tracer, "SearchApi searchOffers").startSpan();
        SearchResponse<OfferResponse> offers = null;
        try (Scope ws = tracer.withSpan(span)) {
            page = Optional.ofNullable(page).orElse(paginationProperties.getDefaultPageValue());
            pageSize = Optional.ofNullable(pageSize).orElse(paginationProperties.getDefaultPageSize());
            offers = searchService.searchOffers(text, page, pageSize);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<SearchResponse<ProductResponse>> searchProducts(
            @RequestParam(value = "text") final String text,
            @RequestHeader(value = "page", required = false) Integer page,
            @RequestHeader(value = "pageSize", required = false) Integer pageSize
    ) {
        Span span = SpanUtils.buildSpan(tracer, "SearchApi searchProducts").startSpan();
        SearchResponse<ProductResponse> products = null;
        try (Scope ws = tracer.withSpan(span)) {
            page = Optional.ofNullable(page).orElse(paginationProperties.getDefaultPageValue());
            pageSize = Optional.ofNullable(pageSize).orElse(paginationProperties.getDefaultPageSize());
            products = searchService.searchProducts(text, page, pageSize);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product-instances")
    public ResponseEntity<SearchResponse<ProductInstanceResponse>> searchProductInstances(
            @RequestParam(value = "text") final String text,
            @RequestHeader(value = "page", required = false) Integer page,
            @RequestHeader(value = "pageSize", required = false) Integer pageSize
    ) {
        Span span = SpanUtils.buildSpan(tracer, "SearchApi searchProductInstances").startSpan();
        SearchResponse<ProductInstanceResponse> productInstances = null;
        try (Scope ws = tracer.withSpan(span)) {
            page = Optional.ofNullable(page).orElse(paginationProperties.getDefaultPageValue());
            pageSize = Optional.ofNullable(pageSize).orElse(paginationProperties.getDefaultPageSize());
            productInstances = searchService.searchProductInstances(text, page, pageSize);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(productInstances, HttpStatus.OK);
    }
}
