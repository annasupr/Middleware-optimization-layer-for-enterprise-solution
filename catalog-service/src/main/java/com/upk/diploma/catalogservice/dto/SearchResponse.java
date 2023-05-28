package com.upk.diploma.catalogservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchResponse<T> {
    private String searchKey;

    private SearchCategory searchCategory;

    private PageResponse<T> searchResults;
}
