package com.upk.diploma.catalogservice.service;

import com.upk.diploma.catalogservice.dto.OfferResponse;

import java.util.List;

public interface OfferService {

    OfferResponse getById(Long id);

    List<OfferResponse> getAll();

    OfferResponse create(OfferResponse offerResponse);

    OfferResponse update(Long offerId, OfferResponse offerResponse);

    void delete(Long offerId);
}
