package com.upk.diploma.service.search.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upk.diploma.config.properties.ExternalServicesProperties;
import com.upk.diploma.dto.SearchResponse;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.service.BasicRestService;
import com.upk.diploma.service.search.SearchService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchServiceImpl extends BasicRestService<SearchResponse> implements SearchService {

    private static final String SEARCH_OFFERS_API_PATH = "/api/public/search/offers?text={text}";
    private static final String SEARCH_PRODUCTS_API_PATH = "/api/public/search/products?text={text}";
    private static final String SEARCH_PRODUCT_INSTANCES_API_PATH = "/api/public/search/product-instances?text={text}";

    public SearchServiceImpl(ExternalServicesProperties externalServicesProperties,
                                  RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(externalServicesProperties, restTemplate, objectMapper, SearchResponse.class);
    }

    @Override
    public SearchResponse<OfferResponse> searchOffers(String text, Integer offset, Integer limit) {
        String url = externalServicesProperties.getCustomerServiceUrl() + SEARCH_OFFERS_API_PATH.replace("{text}", text).replace("{text}", text);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("page", offset.toString());
        httpHeaders.add("pageSize", limit.toString());

        return super.get(url, httpHeaders);
    }

    @Override
    public SearchResponse<ProductResponse> searchProducts(String text, Integer offset, Integer limit) {
        String url = externalServicesProperties.getCustomerServiceUrl() + SEARCH_PRODUCTS_API_PATH.replace("{text}", text);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("page", offset.toString());
        httpHeaders.add("pageSize", limit.toString());

        return super.get(url, httpHeaders);
    }

    @Override
    public SearchResponse<ProductInstanceResponse> searchProductInstances(String text, Integer offset, Integer limit) {
        String url = externalServicesProperties.getCustomerServiceUrl() + SEARCH_PRODUCT_INSTANCES_API_PATH.replace("{text}", text);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("page", offset.toString());
        httpHeaders.add("pageSize", limit.toString());

        return super.get(url, httpHeaders);
    }
}
