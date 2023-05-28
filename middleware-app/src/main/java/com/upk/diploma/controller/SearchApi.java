package com.upk.diploma.controller;

import com.upk.diploma.config.properties.PaginationProperties;
import com.upk.diploma.dto.SearchResponse;
import com.upk.diploma.dto.catalog.OfferResponse;
import com.upk.diploma.dto.catalog.ProductInstanceResponse;
import com.upk.diploma.dto.catalog.ProductResponse;
import com.upk.diploma.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(SearchApi.SEARCH_PUBLIC_API_PATH)
@RequiredArgsConstructor
public class SearchApi {

    private final SearchService searchService;
    private final PaginationProperties paginationProperties;
    public static final String SEARCH_PUBLIC_API_PATH = "/api/public/search";

    @GetMapping("/offers")
    public ResponseEntity<SearchResponse<OfferResponse>> searchOffers(
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        page = Optional.ofNullable(page).orElse(paginationProperties.getDefaultPageValue());
        pageSize = Optional.ofNullable(pageSize).orElse(paginationProperties.getDefaultPageSize());

        final SearchResponse<OfferResponse> offers = searchService.searchOffers(text, page, pageSize);

        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<SearchResponse<ProductResponse>> searchProducts(
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        page = Optional.ofNullable(page).orElse(paginationProperties.getDefaultPageValue());
        pageSize = Optional.ofNullable(pageSize).orElse(paginationProperties.getDefaultPageSize());

        final SearchResponse<ProductResponse> offers = searchService.searchProducts(text, page, pageSize);

        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/product-instances")
    public ResponseEntity<SearchResponse<ProductInstanceResponse>> searchProductInstances(
            @RequestParam(value = "text") final String text,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        page = Optional.ofNullable(page).orElse(paginationProperties.getDefaultPageValue());
        pageSize = Optional.ofNullable(pageSize).orElse(paginationProperties.getDefaultPageSize());

        final SearchResponse<ProductInstanceResponse> offers = searchService.searchProductInstances(text, page, pageSize);

        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
