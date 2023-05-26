package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.ProductInstanceStatusResponse;
import com.upk.diploma.catalogservice.model.entity.ProductInstanceStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductInstanceStatusMapper {

    ProductInstanceStatusResponse map(ProductInstanceStatus productInstanceStatus);

    ProductInstanceStatus map(ProductInstanceStatusResponse productInstanceStatus);

}
