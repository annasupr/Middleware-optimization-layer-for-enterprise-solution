package com.upk.diploma.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInstanceResponse implements Serializable {
    private Long id;
    private ProductResponse product;
    private StorehouseResponse storehouse;
    private ProductInstanceStatusResponse productInstanceStatus;
}