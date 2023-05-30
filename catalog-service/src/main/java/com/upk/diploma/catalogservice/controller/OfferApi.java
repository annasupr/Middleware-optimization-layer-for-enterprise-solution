package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.service.OfferService;
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
@RequestMapping(OfferApi.OFFERS_API_PATH)
@RequiredArgsConstructor
public class OfferApi {
    public static final String OFFERS_API_PATH = "/api/offers";

    private final OfferService offerService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponse> getOfferById(@PathVariable final Long offerId) {
        Span span = SpanUtils.buildSpan(tracer, "OfferApi getOfferById").startSpan();
        OfferResponse foundOffer = null;
        try (Scope ws = tracer.withSpan(span)) {
            foundOffer = offerService.getById(offerId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(foundOffer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OfferResponse>> getAllOffers() {
        Span span = SpanUtils.buildSpan(tracer, "OfferApi getAllOffers").startSpan();
        List<OfferResponse> allOffers = null;
        try (Scope ws = tracer.withSpan(span)) {
            allOffers = offerService.getAll();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(allOffers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(@RequestBody final OfferResponse offerCreateRequest) {
        Span span = SpanUtils.buildSpan(tracer, "OfferApi createOffer").startSpan();
        OfferResponse createdOffer = null;
        try (Scope ws = tracer.withSpan(span)) {
            createdOffer = offerService.create(offerCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<OfferResponse> updateOffer(
            @PathVariable final Long offerId,
            @RequestBody final OfferResponse offerUpdateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "OfferApi updateOffer").startSpan();
        OfferResponse updatedOffer = null;
        try (Scope ws = tracer.withSpan(span)) {
            updatedOffer = offerService.update(offerId, offerUpdateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable final Long offerId) {
        Span span = SpanUtils.buildSpan(tracer, "OfferApi deleteOffer").startSpan();
        try (Scope ws = tracer.withSpan(span)) {
            offerService.delete(offerId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
