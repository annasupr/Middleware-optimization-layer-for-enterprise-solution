package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.ProductCategoryResponse;
import com.upk.diploma.catalogservice.model.entity.ProductCategory;
import com.upk.diploma.catalogservice.repository.ProductCategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductCategoryMapper {

    @Autowired
    protected ProductCategoryRepository productCategoryRepository;

    @Mapping(source = "productCategory.parent.id", target = "parentId")
    public abstract ProductCategoryResponse map(ProductCategory productCategory);

    ProductCategory map(ProductCategoryResponse productCategory){
        if ( productCategory == null ) {
            return null;
        }

        ProductCategory productCategory1 = new ProductCategory();

        productCategory1.setId( productCategory.getId() );
        productCategory1.setName( productCategory.getName() );
        if (productCategory.getParentId() != null) {
            productCategory1.setParent(productCategoryRepository.findById(productCategory.getParentId())
                    .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Product category", productCategory.getParentId())));
        }
        return productCategory1;
    }
}
