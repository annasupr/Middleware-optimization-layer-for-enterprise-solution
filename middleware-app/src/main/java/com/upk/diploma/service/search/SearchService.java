package com.upk.diploma.service.search;

import com.upk.diploma.dto.SearchResponse;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;

public interface SearchService {
    SearchResponse<OfferResponse> searchOffers(String text, Integer offset, Integer limit);

    SearchResponse<ProductResponse> searchProducts(String text, Integer offset, Integer limit);

    SearchResponse<ProductInstanceResponse> searchProductInstances(String text, Integer offset, Integer limit);
}
