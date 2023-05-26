package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.PointOfDistributionResponse;
import com.upk.diploma.catalogservice.model.entity.PointOfDistribution;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PointOfDistributionMapper {

    PointOfDistributionResponse map(PointOfDistribution pointOfDistribution);

    PointOfDistribution map(PointOfDistributionResponse pointOfDistribution);
}
