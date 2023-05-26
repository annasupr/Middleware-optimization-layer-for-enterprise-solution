package com.upk.diploma.catalogservice.service;

import com.upk.diploma.catalogservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    ProductResponse create(ProductResponse productResponse);

    ProductResponse update(Long productId, ProductResponse productResponse);

    void delete(Long productId);
}
