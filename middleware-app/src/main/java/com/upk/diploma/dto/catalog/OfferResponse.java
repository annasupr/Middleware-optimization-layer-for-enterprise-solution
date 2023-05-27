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
public class OfferResponse {
    private Long id;
    private String title;
    private MarketResponse market;
    private PointOfDistributionResponse pointOfDistribution;
}