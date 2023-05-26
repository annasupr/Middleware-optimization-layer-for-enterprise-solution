package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.service.OfferService;
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

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponse> getOfferById(@PathVariable final Long offerId) {
        final OfferResponse foundOffer = offerService.getById(offerId);
        return new ResponseEntity<>(foundOffer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OfferResponse>> getAllOffers() {
        final List<OfferResponse> allOffers = offerService.getAll();
        return new ResponseEntity<>(allOffers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(@RequestBody final OfferResponse offerCreateRequest) {
        final OfferResponse createdOffer = offerService.create(offerCreateRequest);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<OfferResponse> updateOffer(
            @PathVariable final Long offerId,
            @RequestBody final OfferResponse offerUpdateRequest
    ) {
        final OfferResponse updatedOffer = offerService.update(offerId, offerUpdateRequest);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable final Long offerId) {
        offerService.delete(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
