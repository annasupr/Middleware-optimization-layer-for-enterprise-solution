package com.upk.diploma.catalogservice.service;

import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.dto.SearchResponse;

public interface SearchService {

    SearchResponse<OfferResponse> searchOffers(String text, Integer offset, Integer limit);

    SearchResponse<ProductResponse> searchProducts(String text, Integer offset, Integer limit);

    SearchResponse<ProductInstanceResponse> searchProductInstances(String text, Integer offset, Integer limit);
}
