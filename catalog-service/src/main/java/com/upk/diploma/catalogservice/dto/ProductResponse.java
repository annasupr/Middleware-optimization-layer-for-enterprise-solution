package com.upk.diploma.catalogservice.dto;

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
public class ProductResponse {
    private Long id;
    private ProductCategoryResponse productCategory;
    private String title;
    private String description;
    private String imageUrl;
    private DurationResponse duration;
    private Integer memoryMb;
}