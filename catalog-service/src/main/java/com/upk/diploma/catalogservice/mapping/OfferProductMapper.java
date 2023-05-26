package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.OfferProductResponse;
import com.upk.diploma.catalogservice.model.entity.OfferProduct;
import com.upk.diploma.catalogservice.repository.OfferRepository;
import com.upk.diploma.catalogservice.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OfferProductMapper {

    @Autowired
    protected OfferRepository offerRepository;

    @Autowired
    protected ProductRepository productRepository;

    public abstract OfferProductResponse map(OfferProduct offerProduct);

    public OfferProduct map(OfferProductResponse offerProduct) {
        if (offerProduct == null) {
            return null;
        }

        OfferProduct.OfferProductBuilder offerProductBuilder = OfferProduct.builder();

        offerProductBuilder.id(offerProduct.getId());
        offerProductBuilder.offer(offerRepository.findById(offerProduct.getOfferId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Offer", offerProduct.getProductId())));
        offerProductBuilder.product(productRepository.findById(offerProduct.getProductId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Product", offerProduct.getProductId())));
        offerProductBuilder.price(offerProduct.getPrice());

        return offerProductBuilder.build();
    }
}
