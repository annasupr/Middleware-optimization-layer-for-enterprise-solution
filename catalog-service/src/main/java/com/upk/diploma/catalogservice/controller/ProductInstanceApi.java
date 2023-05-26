package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.service.ProductInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductInstanceApi.PRODUCT_INSTANCES_API_PATH)
@RequiredArgsConstructor
public class ProductInstanceApi {
    public static final String PRODUCT_INSTANCES_API_PATH = "/api/product-instances";

    private final ProductInstanceService productInstanceService;

    @GetMapping("/{productInstanceId}")
    public ResponseEntity<ProductInstanceResponse> getProductInstanceById(@PathVariable final Long productInstanceId) {
        final ProductInstanceResponse foundProductInstance = productInstanceService.getById(productInstanceId);
        return new ResponseEntity<>(foundProductInstance, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductInstanceResponse>> getAllProductInstances() {
        final List<ProductInstanceResponse> allProductInstances = productInstanceService.getAll();
        return new ResponseEntity<>(allProductInstances, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductInstanceResponse> createProductInstance(
            @RequestBody final ProductInstanceResponse productInstanceCreateRequest) {
        final ProductInstanceResponse createdProductInstance = productInstanceService.create(productInstanceCreateRequest);
        return new ResponseEntity<>(createdProductInstance, HttpStatus.CREATED);
    }

    @PutMapping("/{productInstanceId}")
    public ResponseEntity<ProductInstanceResponse> updateProductInstance(
            @PathVariable final Long productInstanceId,
            @RequestBody final ProductInstanceResponse productInstanceUpdateRequest) {
        final ProductInstanceResponse updatedProductInstance =
                productInstanceService.update(productInstanceId, productInstanceUpdateRequest);
        return new ResponseEntity<>(updatedProductInstance, HttpStatus.OK);
    }

    @DeleteMapping("/{productInstanceId}")
    public ResponseEntity<Void> deleteProductInstance(@PathVariable final Long productInstanceId) {
        productInstanceService.delete(productInstanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
