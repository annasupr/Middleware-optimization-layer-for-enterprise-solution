package com.upk.diploma.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInstanceResponse {
    private Long id;
    private ProductResponse product;
    private StorehouseResponse storehouse;
    private ProductInstanceStatusResponse productInstanceStatus;
}