package com.upk.diploma.catalogservice.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataTextSearcher<T> {

    @PersistenceContext
    private final EntityManager entityManager;

    private static final String[] allMainOfferFields = {
            "title",
            "description",
            "market.name",
            "pointOfDistribution.name"
    };

    private static final String[] allMainProductFields = {
            "title",
            "description",
            "memoryMb",
            "productCategory.name",
            "duration.name"
    };

    private static final String[] allMainProductInstanceFields = {
            "product.title",
            "storehouse.name",
            "storehouse.address",
            "productInstanceStatus.name"
    };

    public List<T> searchDataByAllMainFields(final Class<T> type, final String text, final Integer offset, final Integer limit) {
        switch (type.getSimpleName()) {
            case "Offer":
                return searchData(type, text, offset, limit, allMainOfferFields);
            case "Product":
                return searchData(type, text, offset, limit, allMainProductFields);
            case "ProductInstance":
                return searchData(type, text, offset, limit, allMainProductInstanceFields);
            default:
                return searchData(type, text, offset, limit);
        }
    }

    private List<T> searchData(final Class<T> type, final String text, final Integer offset, final Integer limit,
                               final String... fields) {
        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager( entityManager);

        final QueryBuilder queryBuilder = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(type)
                .get();

        final Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .onFields(fields)
                .matching(text)
                .createQuery();

        final FullTextQuery jpaQuery = fullTextEntityManager
                .createFullTextQuery(query, type);

        jpaQuery.setMaxResults(limit);
        jpaQuery.setFirstResult(offset);

        return (List<T>) jpaQuery.getResultList();
    }
}
