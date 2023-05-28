package com.upk.diploma.service.catalog.impl;

import com.upk.diploma.config.properties.ExternalServicesProperties;
import com.upk.diploma.dto.catalog.DurationResponse;
import com.upk.diploma.dto.catalog.MarketResponse;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.PointOfDistributionResponse;
import com.upk.diploma.dto.catalog.ProductCategoryResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.dto.catalog.StorehouseResponse;
import com.upk.diploma.service.catalog.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final RestTemplate restTemplate;
    
    private final ExternalServicesProperties externalServicesProperties;

    public static final String CATALOG_API_PATH = "/api/catalog";
    public static final String OFFERS_API_PATH = "/api/offers";
    public static final String PRODUCTS_API_PATH = "/api/products";
    public static final String PRODUCT_INSTANCES_API_PATH = "/api/product-instances";

    @Override
    public List<StorehouseResponse> getAllStorehouses() {
        String url = externalServicesProperties.getCatalogServiceUrl() + CATALOG_API_PATH + "/storehouses";
        ResponseEntity<List<StorehouseResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StorehouseResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public List<MarketResponse> getAllMarkets() {
        String url = externalServicesProperties.getCatalogServiceUrl() + CATALOG_API_PATH + "/markets";
        ResponseEntity<List<MarketResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MarketResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public List<PointOfDistributionResponse> getAllPointsOfDistribution() {
        String url = externalServicesProperties.getCatalogServiceUrl() + CATALOG_API_PATH + "/points-of-distribution";
        ResponseEntity<List<PointOfDistributionResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PointOfDistributionResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public List<DurationResponse> getAllDurations() {
        String url = externalServicesProperties.getCatalogServiceUrl() + CATALOG_API_PATH + "/durations";
        ResponseEntity<List<DurationResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DurationResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {
        String url = externalServicesProperties.getCatalogServiceUrl() + CATALOG_API_PATH + "/product-categories";
        ResponseEntity<List<ProductCategoryResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductCategoryResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public OfferResponse getOfferById(Long id) {
        String url = externalServicesProperties.getCatalogServiceUrl() + OFFERS_API_PATH + "/" + id;
        ResponseEntity<OfferResponse> response = restTemplate.getForEntity(url, OfferResponse.class);
        return response.getBody();
    }

    @Override
    public List<OfferResponse> getAllOffers() {
        String url = externalServicesProperties.getCatalogServiceUrl() + OFFERS_API_PATH;
        ResponseEntity<List<OfferResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OfferResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public ProductInstanceResponse getProductInstanceById(Long id) {
        String url = externalServicesProperties.getCatalogServiceUrl() + PRODUCT_INSTANCES_API_PATH + "/" + id;
        ResponseEntity<ProductInstanceResponse> response = restTemplate.getForEntity(url, ProductInstanceResponse.class);
        return response.getBody();
    }

    @Override
    public List<ProductInstanceResponse> getAllProductInstances() {
        String url = externalServicesProperties.getCatalogServiceUrl() + PRODUCT_INSTANCES_API_PATH;
        ResponseEntity<List<ProductInstanceResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductInstanceResponse>>() {}
        );
        return response.getBody();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        String url = externalServicesProperties.getCatalogServiceUrl() + PRODUCTS_API_PATH + "/" + id;
        ResponseEntity<ProductResponse> response = restTemplate.getForEntity(url, ProductResponse.class);
        return response.getBody();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        String url = externalServicesProperties.getCatalogServiceUrl() + PRODUCTS_API_PATH;
        ResponseEntity<List<ProductResponse>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductResponse>>() {}
        );
        return response.getBody();
    }
}
