package com.upk.diploma.service.catalog;

import com.upk.diploma.dto.catalog.DurationResponse;
import com.upk.diploma.dto.catalog.MarketResponse;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.PointOfDistributionResponse;
import com.upk.diploma.dto.catalog.ProductCategoryResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.dto.catalog.StorehouseResponse;

import java.util.List;

public interface CatalogService {

    List<StorehouseResponse> getAllStorehouses();

    List<MarketResponse> getAllMarkets();

    List<PointOfDistributionResponse> getAllPointsOfDistribution();

    List<DurationResponse> getAllDurations();

    List<ProductCategoryResponse> getAllProductCategories();

    OfferResponse getOfferById(Long id);

    List<OfferResponse> getAllOffers();

    ProductInstanceResponse getProductInstanceById(Long id);

    List<ProductInstanceResponse> getAllProductInstances();

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();
}
