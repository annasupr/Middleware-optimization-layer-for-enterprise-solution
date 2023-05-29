package com.upk.diploma.service.catalog;

import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;

public interface ModifyCatalogService {
    OfferResponse create(OfferResponse offerResponse);

    OfferResponse updateOffer(Long offerId, OfferResponse offerResponse);

    void deleteOffer(Long offerId);

    ProductResponse createProduct(ProductResponse productResponse);

    ProductResponse updateProduct(Long productId, ProductResponse productResponse);

    void delete(Long productId);

    ProductInstanceResponse create(ProductInstanceResponse productInstanceResponse);

    ProductInstanceResponse update(Long productInstanceId, ProductInstanceResponse productInstanceResponse);

    void deleteProductInstance(Long productInstanceId);
}
