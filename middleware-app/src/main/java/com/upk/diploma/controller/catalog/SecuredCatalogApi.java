package com.upk.diploma.controller.catalog;

import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.service.catalog.ModifyCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SecuredCatalogApi.SECURED_CATALOG_API_PATH)
@RequiredArgsConstructor
public class SecuredCatalogApi {
    public static final String SECURED_CATALOG_API_PATH = "/api/secured/catalog";

    private final ModifyCatalogService modifyCatalogService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/offers")
    public ResponseEntity<OfferResponse> createOffer(@RequestBody OfferResponse offerResponse) {
        OfferResponse createdOffer = modifyCatalogService.create(offerResponse);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/offers/{offerId}")
    public ResponseEntity<OfferResponse> updateOffer(@PathVariable("offerId") Long offerId, @RequestBody OfferResponse offerResponse) {
        OfferResponse updatedOffer = modifyCatalogService.updateOffer(offerId, offerResponse);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/offers/{offerId}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("offerId") Long offerId) {
        modifyCatalogService.deleteOffer(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductResponse productResponse) {
        ProductResponse createdProduct = modifyCatalogService.createProduct(productResponse);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductResponse productResponse) {
        ProductResponse updatedProduct = modifyCatalogService.updateProduct(productId, productResponse);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        modifyCatalogService.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product-instances")
    public ResponseEntity<ProductInstanceResponse> createProductInstance(@RequestBody ProductInstanceResponse productInstanceResponse) {
        ProductInstanceResponse createdProductInstance = modifyCatalogService.create(productInstanceResponse);
        return new ResponseEntity<>(createdProductInstance, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/product-instances/{productInstanceId}")
    public ResponseEntity<ProductInstanceResponse> updateProductInstance(@PathVariable("productInstanceId") Long productInstanceId, @RequestBody ProductInstanceResponse productInstanceResponse) {
        ProductInstanceResponse updatedProductInstance = modifyCatalogService.update(productInstanceId, productInstanceResponse);
        return new ResponseEntity<>(updatedProductInstance, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/product-instances/{productInstanceId}")
    public ResponseEntity<Void> deleteProductInstance(@PathVariable("productInstanceId") Long productInstanceId) {
        modifyCatalogService.deleteProductInstance(productInstanceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
