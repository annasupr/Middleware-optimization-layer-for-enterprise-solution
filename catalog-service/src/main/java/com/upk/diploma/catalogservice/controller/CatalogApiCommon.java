package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.DurationResponse;
import com.upk.diploma.catalogservice.dto.MarketResponse;
import com.upk.diploma.catalogservice.dto.PointOfDistributionResponse;
import com.upk.diploma.catalogservice.dto.ProductCategoryResponse;
import com.upk.diploma.catalogservice.dto.StorehouseResponse;
import com.upk.diploma.catalogservice.service.CatalogServiceCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CatalogApiCommon.CATALOG_API_PATH)
@RequiredArgsConstructor
public class CatalogApiCommon {
    public static final String CATALOG_API_PATH = "/api/catalog";

    private final CatalogServiceCommon catalogService;

    @GetMapping("/storehouses")
    public ResponseEntity<List<StorehouseResponse>> getAllStorehouses() {
        List<StorehouseResponse> storehouses = catalogService.getAllStorehouses();
        return new ResponseEntity<>(storehouses, HttpStatus.OK);
    }

    @GetMapping("/markets")
    public ResponseEntity<List<MarketResponse>> getAllMarkets() {
        List<MarketResponse> markets = catalogService.getAllMarkets();
        return new ResponseEntity<>(markets, HttpStatus.OK);
    }

    @GetMapping("/points-of-distribution")
    public ResponseEntity<List<PointOfDistributionResponse>> getAllPointsOfDistribution() {
        List<PointOfDistributionResponse> pointsOfDistribution = catalogService.getAllPointsOfDistribution();
        return new ResponseEntity<>(pointsOfDistribution, HttpStatus.OK);
    }

    @GetMapping("/durations")
    public ResponseEntity<List<DurationResponse>> getAllDurations() {
        List<DurationResponse> durations = catalogService.getAllDurations();
        return new ResponseEntity<>(durations, HttpStatus.OK);
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories() {
        List<ProductCategoryResponse> productCategories = catalogService.getAllProductCategories();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }
}
