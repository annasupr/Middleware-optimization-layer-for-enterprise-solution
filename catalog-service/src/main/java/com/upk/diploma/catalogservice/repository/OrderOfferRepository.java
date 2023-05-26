package com.upk.diploma.catalogservice.repository;

import com.upk.diploma.catalogservice.model.entity.OrderOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOfferRepository extends JpaRepository<OrderOffer, Long> {
}
