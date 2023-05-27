package com.upk.diploma.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInstanceResponse {
    private Long id;
    private ProductResponse product;
    private StorehouseResponse storehouse;
    private ProductInstanceStatusResponse productInstanceStatus;
}