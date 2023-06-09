package com.upk.diploma.catalogservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
@RequiredArgsConstructor
public class BuildSearchService implements ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            throw new RuntimeException("An error occurred trying to build the search index", e); // todo
        }
    }
}
