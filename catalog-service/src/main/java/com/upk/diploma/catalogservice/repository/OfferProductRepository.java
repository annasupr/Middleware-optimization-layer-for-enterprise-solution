package com.upk.diploma.catalogservice.repository;

import com.upk.diploma.catalogservice.model.entity.OfferProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferProductRepository extends JpaRepository<OfferProduct, Long> {
}
