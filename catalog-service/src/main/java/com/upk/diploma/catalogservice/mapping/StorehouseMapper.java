package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.StorehouseResponse;
import com.upk.diploma.catalogservice.model.entity.Storehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorehouseMapper {
    StorehouseResponse map(Storehouse storehouse);

    Storehouse map(StorehouseResponse storehouse);

}
