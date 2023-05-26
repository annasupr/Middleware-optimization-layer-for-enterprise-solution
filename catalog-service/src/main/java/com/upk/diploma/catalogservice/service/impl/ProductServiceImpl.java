package com.upk.diploma.catalogservice.service.impl;

import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.exception.ConversionException;
import com.upk.diploma.catalogservice.exception.DataNotFoundException;
import com.upk.diploma.catalogservice.mapping.ProductMapper;
import com.upk.diploma.catalogservice.model.entity.Product;
import com.upk.diploma.catalogservice.repository.ProductRepository;
import com.upk.diploma.catalogservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse getById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::map)
                .orElseThrow(() -> new DataNotFoundException("Product", id));
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse create(ProductResponse productResponse) {
        final Product product = productMapper.map(productResponse);
        final Product savedProduct = productRepository.save(product);
        return productMapper.map(savedProduct);
    }

    @Override
    public ProductResponse update(Long productId, ProductResponse productResponse) {
        final Product product = Optional.ofNullable(productMapper.map(productResponse))
                .orElseThrow(() -> new ConversionException("Product"));
        product.setId(productId);

        final Product savedProduct = productRepository.save(product);
        return productMapper.map(savedProduct);
    }

    @Override
    public void delete(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Product", productId);
        }
    }
}
