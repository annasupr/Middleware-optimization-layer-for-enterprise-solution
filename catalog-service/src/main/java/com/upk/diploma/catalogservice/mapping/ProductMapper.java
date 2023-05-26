package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.model.entity.Product;
import com.upk.diploma.catalogservice.repository.DurationRepository;
import com.upk.diploma.catalogservice.repository.ProductCategoryRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ProductCategoryMapper.class, DurationMapper.class})
public abstract class ProductMapper {

    @Autowired
    protected ProductCategoryRepository productCategoryRepository;

    @Autowired
    protected DurationRepository durationRepository;

    public abstract ProductResponse map(Product product);

    public Product map(ProductResponse product){
        if ( product == null ) {
            return null;
        }

        Product product1 = new Product();

        product1.setId( product.getId() );
        product1.setProductCategory( productCategoryRepository.findById(product.getProductCategory().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Product category", product.getProductCategory().getId())) );
        product1.setTitle( product.getTitle() );
        product1.setDescription( product.getDescription() );
        product1.setImageUrl( product.getImageUrl() );
        product1.setDuration( durationRepository.findById(product.getDuration().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Duration", product.getDuration().getId())) );
        product1.setMemoryMb( product.getMemoryMb() );

        return product1;
    }
}
