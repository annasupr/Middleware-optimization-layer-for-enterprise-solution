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
public class OfferProductResponse {
    private Long id;
    private Long offerId;
    private Long productId;
    private Double price;
    private Integer quantity;
}