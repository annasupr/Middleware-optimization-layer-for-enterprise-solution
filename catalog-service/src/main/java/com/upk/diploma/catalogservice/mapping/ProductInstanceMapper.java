package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.model.entity.ProductInstance;
import com.upk.diploma.catalogservice.repository.ProductInstanceStatusRepository;
import com.upk.diploma.catalogservice.repository.ProductRepository;
import com.upk.diploma.catalogservice.repository.StorehouseRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, StorehouseMapper.class, ProductInstanceStatusMapper.class})
public abstract class ProductInstanceMapper {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected StorehouseRepository storeHouseRepository;

    @Autowired
    protected ProductInstanceStatusRepository productInstanceStatusRepository;

    public abstract ProductInstanceResponse map(ProductInstance productInstance);

    public ProductInstance map(ProductInstanceResponse productInstance){
        if ( productInstance == null ) {
            return null;
        }

        ProductInstance productInstance1 = new ProductInstance();

        productInstance1.setId( productInstance.getId() );
        productInstance1.setProduct( productRepository.findById(productInstance.getProduct().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Product", productInstance.getProduct().getId())) );
        productInstance1.setStorehouse( storeHouseRepository.findById(productInstance.getStorehouse().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Storehouse", productInstance.getStorehouse().getId())) );
        productInstance1.setProductInstanceStatus( productInstanceStatusRepository.findById(productInstance.getProductInstanceStatus().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Product instance status", productInstance.getProductInstanceStatus().getId())) );

        return productInstance1;
    }
}
