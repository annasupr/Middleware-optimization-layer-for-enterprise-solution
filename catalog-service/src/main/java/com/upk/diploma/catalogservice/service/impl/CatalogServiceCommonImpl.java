package com.upk.diploma.catalogservice.service.impl;

import com.upk.diploma.catalogservice.dto.DurationResponse;
import com.upk.diploma.catalogservice.dto.MarketResponse;
import com.upk.diploma.catalogservice.dto.PointOfDistributionResponse;
import com.upk.diploma.catalogservice.dto.ProductCategoryResponse;
import com.upk.diploma.catalogservice.dto.StorehouseResponse;
import com.upk.diploma.catalogservice.mapping.DurationMapper;
import com.upk.diploma.catalogservice.mapping.MarketMapper;
import com.upk.diploma.catalogservice.mapping.PointOfDistributionMapper;
import com.upk.diploma.catalogservice.mapping.ProductCategoryMapper;
import com.upk.diploma.catalogservice.mapping.StorehouseMapper;
import com.upk.diploma.catalogservice.repository.DurationRepository;
import com.upk.diploma.catalogservice.repository.MarketRepository;
import com.upk.diploma.catalogservice.repository.PointOfDistributionRepository;
import com.upk.diploma.catalogservice.repository.ProductCategoryRepository;
import com.upk.diploma.catalogservice.repository.StorehouseRepository;
import com.upk.diploma.catalogservice.service.CatalogServiceCommon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogServiceCommonImpl implements CatalogServiceCommon {

    private final StorehouseRepository storehouseRepository;
    private final MarketRepository marketRepository;
    private final PointOfDistributionRepository pointOfDistributionRepository;
    private final DurationRepository durationRepository;
    private final ProductCategoryRepository productCategoryRepository;

    private final StorehouseMapper storehouseMapper;
    private final MarketMapper marketMapper;
    private final PointOfDistributionMapper pointOfDistributionMapper;
    private final DurationMapper durationMapper;
    private final ProductCategoryMapper productCategoryMapper;

    @Override
    public List<StorehouseResponse> getAllStorehouses() {
        return storehouseRepository.findAll()
                .stream()
                .map(storehouseMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarketResponse> getAllMarkets() {
        return marketRepository.findAll()
                .stream()
                .map(marketMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<PointOfDistributionResponse> getAllPointsOfDistribution() {
        return pointOfDistributionRepository.findAll()
                .stream()
                .map(pointOfDistributionMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<DurationResponse> getAllDurations() {
        return durationRepository.findAll()
                .stream()
                .map(durationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategoryMapper::map)
                .collect(Collectors.toList());
    }
}
