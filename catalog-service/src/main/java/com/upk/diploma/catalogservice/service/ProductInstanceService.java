package com.upk.diploma.catalogservice.service;


import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;

import java.util.List;

public interface ProductInstanceService {

    ProductInstanceResponse getById(Long id);

    List<ProductInstanceResponse> getAll();

    ProductInstanceResponse create(ProductInstanceResponse productInstanceResponse);

    ProductInstanceResponse update(Long productInstanceId, ProductInstanceResponse productInstanceResponse);

    void delete(Long productInstanceId);
}
