package com.upk.diploma.catalogservice.repository;

import com.upk.diploma.catalogservice.model.entity.PointOfDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfDistributionRepository extends JpaRepository<PointOfDistribution, Long> {
}
