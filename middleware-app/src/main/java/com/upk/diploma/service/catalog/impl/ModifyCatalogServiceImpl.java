package com.upk.diploma.service.catalog.impl;

import com.upk.diploma.config.properties.ExternalServicesProperties;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.service.catalog.ModifyCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ModifyCatalogServiceImpl implements ModifyCatalogService {
    private static final String OFFER_API_PATH = "/api/offers";
    private static final String PRODUCT_API_PATH = "/api/products";
    private static final String PRODUCT_INSTANCE_API_PATH = "/api/product-instances";

    private final RestTemplate restTemplate;
    private final ExternalServicesProperties externalServicesProperties;

    @Override
    public OfferResponse create(OfferResponse offerResponse) {
        String url = getFullUrl(OFFER_API_PATH);
        return restTemplate.postForObject(url, offerResponse, OfferResponse.class);
    }

    @CachePut(value = "offers", key = "#offerId")
    @Override
    public OfferResponse updateOffer(Long offerId, OfferResponse offerResponse) {
        String url = getFullUrl(OFFER_API_PATH + "/" + offerId);
        HttpEntity<OfferResponse> requestEntity = new HttpEntity<>(offerResponse);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, OfferResponse.class).getBody();
    }

    @CacheEvict(value = "offers", allEntries=true)
    @Override
    public void deleteOffer(Long offerId) {
        String url = getFullUrl(OFFER_API_PATH + "/" + offerId);
        restTemplate.delete(url);
    }

    @Override
    public ProductResponse createProduct(ProductResponse productResponse) {
        String url = getFullUrl(PRODUCT_API_PATH);
        return restTemplate.postForObject(url, productResponse, ProductResponse.class);
    }

    @CachePut(value = "products", key = "#productId")
    @Override
    public ProductResponse updateProduct(Long productId, ProductResponse productResponse) {
        String url = getFullUrl(PRODUCT_API_PATH + "/" + productId);
        HttpEntity<ProductResponse> requestEntity = new HttpEntity<>(productResponse);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ProductResponse.class).getBody();
    }

    @CacheEvict(value = "products", allEntries=true)
    @Override
    public void delete(Long productId) {
        String url = getFullUrl(PRODUCT_API_PATH + "/" + productId);
        restTemplate.delete(url);
    }

    @Override
    public ProductInstanceResponse create(ProductInstanceResponse productInstanceResponse) {
        String url = getFullUrl(PRODUCT_INSTANCE_API_PATH);
        return restTemplate.postForObject(url, productInstanceResponse, ProductInstanceResponse.class);
    }

    @CachePut(value = "product-instances", key = "#productInstanceId")
    @Override
    public ProductInstanceResponse update(Long productInstanceId, ProductInstanceResponse productInstanceResponse) {
        String url = getFullUrl(PRODUCT_INSTANCE_API_PATH + "/" + productInstanceId);
        HttpEntity<ProductInstanceResponse> requestEntity = new HttpEntity<>(productInstanceResponse);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ProductInstanceResponse.class).getBody();
    }

    @CacheEvict(value = "product-instances", allEntries=true)
    @Override
    public void deleteProductInstance(Long productInstanceId) {
        String url = getFullUrl(PRODUCT_INSTANCE_API_PATH + "/" + productInstanceId);
        restTemplate.delete(url);
    }

    private String getFullUrl(String path) {
        return externalServicesProperties.getCatalogServiceUrl() + path;
    }
}
