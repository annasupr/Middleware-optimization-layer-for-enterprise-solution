package com.upk.diploma.catalogservice.controller;

import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductApi.PRODUCTS_API_PATH)
@RequiredArgsConstructor
public class ProductApi {
    public static final String PRODUCTS_API_PATH = "/api/products";

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable final Long productId) {
        final ProductResponse foundProduct = productService.getById(productId);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        final List<ProductResponse> allProducts = productService.getAll();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody final ProductResponse productCreateRequest) {
        final ProductResponse createdProduct = productService.create(productCreateRequest);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable final Long productId,
            @RequestBody final ProductResponse productUpdateRequest
    ) {
        final ProductResponse updatedProduct = productService.update(productId, productUpdateRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable final Long productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
