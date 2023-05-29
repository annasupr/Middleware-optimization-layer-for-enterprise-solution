package com.upk.diploma.controller.catalog;

import com.upk.diploma.dto.catalog.DurationResponse;
import com.upk.diploma.dto.catalog.MarketResponse;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.PointOfDistributionResponse;
import com.upk.diploma.dto.catalog.ProductCategoryResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.dto.catalog.StorehouseResponse;
import com.upk.diploma.service.catalog.ReadCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PublicCatalogApi.PUBLIC_CATALOG_API_PATH)
@RequiredArgsConstructor
public class PublicCatalogApi {
    public static final String PUBLIC_CATALOG_API_PATH = "/api/public/catalog";

    private final ReadCatalogService readCatalogService;

    @GetMapping("/storehouses")
    public ResponseEntity<List<StorehouseResponse>> getAllStorehouses() {
        List<StorehouseResponse> storehouses = readCatalogService.getAllStorehouses();
        return new ResponseEntity<>(storehouses, HttpStatus.OK);
    }

    @GetMapping("/markets")
    public ResponseEntity<List<MarketResponse>> getAllMarkets() {
        List<MarketResponse> markets = readCatalogService.getAllMarkets();
        return new ResponseEntity<>(markets, HttpStatus.OK);
    }

    @GetMapping("/points-of-distribution")
    public ResponseEntity<List<PointOfDistributionResponse>> getAllPointsOfDistribution() {
        List<PointOfDistributionResponse> pointsOfDistribution = readCatalogService.getAllPointsOfDistribution();
        return new ResponseEntity<>(pointsOfDistribution, HttpStatus.OK);
    }

    @GetMapping("/durations")
    public ResponseEntity<List<DurationResponse>> getAllDurations() {
        List<DurationResponse> durations = readCatalogService.getAllDurations();
        return new ResponseEntity<>(durations, HttpStatus.OK);
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories() {
        List<ProductCategoryResponse> productCategories = readCatalogService.getAllProductCategories();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<OfferResponse> getOfferById(@PathVariable("id") Long id) {
        OfferResponse offer = readCatalogService.getOfferById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping("/offers")
    public ResponseEntity<List<OfferResponse>> getAllOffers() {
        List<OfferResponse> offers = readCatalogService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/product-instances/{id}")
    public ResponseEntity<ProductInstanceResponse> getProductInstanceById(@PathVariable("id") Long id) {
        ProductInstanceResponse productInstance = readCatalogService.getProductInstanceById(id);
        return new ResponseEntity<>(productInstance, HttpStatus.OK);
    }

    @GetMapping("/product-instances")
    public ResponseEntity<List<ProductInstanceResponse>> getAllProductInstances() {
        List<ProductInstanceResponse> productInstances = readCatalogService.getAllProductInstances();
        return new ResponseEntity<>(productInstances, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
        ProductResponse product = readCatalogService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = readCatalogService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

