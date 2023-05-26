package com.upk.diploma.catalogservice.service.impl;

import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.exception.ConversionException;
import com.upk.diploma.catalogservice.exception.DataNotFoundException;
import com.upk.diploma.catalogservice.mapping.ProductInstanceMapper;
import com.upk.diploma.catalogservice.model.entity.ProductInstance;
import com.upk.diploma.catalogservice.repository.ProductInstanceRepository;
import com.upk.diploma.catalogservice.service.ProductInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductInstanceServiceImpl implements ProductInstanceService {

    private final ProductInstanceRepository productInstanceRepository;
    private final ProductInstanceMapper productInstanceMapper;

    @Override
    public ProductInstanceResponse getById(Long id) {
        return productInstanceRepository.findById(id)
                .map(productInstanceMapper::map)
                .orElseThrow(() -> new DataNotFoundException("Product Instance", id));
    }

    @Override
    public List<ProductInstanceResponse> getAll() {
        return productInstanceRepository.findAll()
                .stream()
                .map(productInstanceMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ProductInstanceResponse create(ProductInstanceResponse productInstanceResponse) {
        final ProductInstance productInstance = productInstanceMapper.map(productInstanceResponse);
        final ProductInstance savedProductInstance = productInstanceRepository.save(productInstance);
        return productInstanceMapper.map(savedProductInstance);
    }

    @Override
    public ProductInstanceResponse update(Long productInstanceId, ProductInstanceResponse productInstanceResponse) {
        final ProductInstance productInstance = Optional.ofNullable(productInstanceMapper.map(productInstanceResponse))
                .orElseThrow(() -> new ConversionException("Product Instance"));
        productInstance.setId(productInstanceId);

        final ProductInstance savedProductInstance = productInstanceRepository.save(productInstance);
        return productInstanceMapper.map(savedProductInstance);
    }

    @Override
    public void delete(Long productInstanceId) {
        try {
            productInstanceRepository.deleteById(productInstanceId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Product Instance", productInstanceId);
        }
    }
}
