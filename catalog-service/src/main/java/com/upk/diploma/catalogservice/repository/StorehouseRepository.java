package com.upk.diploma.catalogservice.repository;

import com.upk.diploma.catalogservice.model.entity.Storehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorehouseRepository extends JpaRepository<Storehouse, Long> {
}
