package com.upk.diploma.catalogservice.service;

import com.upk.diploma.catalogservice.dto.DurationResponse;
import com.upk.diploma.catalogservice.dto.MarketResponse;
import com.upk.diploma.catalogservice.dto.PointOfDistributionResponse;
import com.upk.diploma.catalogservice.dto.ProductCategoryResponse;
import com.upk.diploma.catalogservice.dto.StorehouseResponse;

import java.util.List;

public interface CatalogServiceCommon {
    List<StorehouseResponse> getAllStorehouses();

    List<MarketResponse> getAllMarkets();

    List<PointOfDistributionResponse> getAllPointsOfDistribution();

    List<DurationResponse> getAllDurations();

    List<ProductCategoryResponse> getAllProductCategories();
}
