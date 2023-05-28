package com.upk.diploma.catalogservice.service.impl;

import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.dto.PageResponse;
import com.upk.diploma.catalogservice.dto.ProductInstanceResponse;
import com.upk.diploma.catalogservice.dto.ProductResponse;
import com.upk.diploma.catalogservice.dto.SearchCategory;
import com.upk.diploma.catalogservice.dto.SearchResponse;
import com.upk.diploma.catalogservice.mapping.OfferMapper;
import com.upk.diploma.catalogservice.mapping.ProductInstanceMapper;
import com.upk.diploma.catalogservice.mapping.ProductMapper;
import com.upk.diploma.catalogservice.model.entity.Offer;
import com.upk.diploma.catalogservice.model.entity.Product;
import com.upk.diploma.catalogservice.model.entity.ProductInstance;
import com.upk.diploma.catalogservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final DataTextSearcher<Offer> offerTextSearcher;
    private final DataTextSearcher<Product> productTextSearcher;
    private final DataTextSearcher<ProductInstance> productInstanceTextSearcher;

    private final OfferMapper offerMapper;
    private final ProductMapper productMapper;
    private final ProductInstanceMapper productInstanceMapper;

    @Override
    public SearchResponse<OfferResponse> searchOffers(String text, Integer offset, Integer limit) {
        final List<Offer> offers =
                offerTextSearcher.searchDataByAllMainFields(Offer.class, text, offset, limit);
        final List<OfferResponse> offersDto = offers.stream()
                .map(offerMapper::map)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        final PageResponse<OfferResponse> offersPageResponse = PageResponse.<OfferResponse>builder()
                .content(offersDto)
                .numberOfElements(offersDto.size())
                .number(offset)
                .size(limit)
                .build();

        return SearchResponse.<OfferResponse>builder()
                .searchKey(text)
                .searchCategory(SearchCategory.OFFERS)
                .searchResults(offersPageResponse)
                .build();
    }

    @Override
    public SearchResponse<ProductResponse> searchProducts(String text, Integer offset, Integer limit) {
        final List<Product> products =
                productTextSearcher.searchDataByAllMainFields(Product.class, text, offset, limit);
        final List<ProductResponse> productsDto = products.stream()
                .map(productMapper::map)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        final PageResponse<ProductResponse> productPageResponse = PageResponse.<ProductResponse>builder()
                .content(productsDto)
                .numberOfElements(productsDto.size())
                .number(offset)
                .size(limit)
                .build();

        return SearchResponse.<ProductResponse>builder()
                .searchKey(text)
                .searchCategory(SearchCategory.PRODUCTS)
                .searchResults(productPageResponse)
                .build();
    }

    @Override
    public SearchResponse<ProductInstanceResponse> searchProductInstances(String text, Integer offset, Integer limit) {
        final List<ProductInstance> products =
                productInstanceTextSearcher.searchDataByAllMainFields(ProductInstance.class, text, offset, limit);
        final List<ProductInstanceResponse> productsDto = products.stream()
                .map(productInstanceMapper::map)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        final PageResponse<ProductInstanceResponse> productPageResponse = PageResponse.<ProductInstanceResponse>builder()
                .content(productsDto)
                .numberOfElements(productsDto.size())
                .number(offset)
                .size(limit)
                .build();

        return SearchResponse.<ProductInstanceResponse>builder()
                .searchKey(text)
                .searchCategory(SearchCategory.PRODUCT_INSTANCES)
                .searchResults(productPageResponse)
                .build();
    }
}
