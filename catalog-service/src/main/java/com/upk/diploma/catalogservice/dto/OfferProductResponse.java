package com.upk.diploma.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OfferProductResponse {
    private Long id;
    private OfferResponse offer;
    private ProductResponse product;
    private Double price;
    private Integer quantity;
}