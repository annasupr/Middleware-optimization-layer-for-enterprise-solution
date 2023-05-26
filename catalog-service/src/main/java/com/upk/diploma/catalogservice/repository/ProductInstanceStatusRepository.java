package com.upk.diploma.catalogservice.repository;

import com.upk.diploma.catalogservice.model.entity.ProductInstanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInstanceStatusRepository extends JpaRepository<ProductInstanceStatus, Long> {
}
