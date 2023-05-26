package com.upk.diploma.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderOfferResponse {
    private Long id;
    private OfferResponse offer;
    private Long order;
    private Integer quantity;
    private Double totalPrice;
}