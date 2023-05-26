package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.DurationResponse;
import com.upk.diploma.catalogservice.model.entity.Duration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DurationMapper {

    DurationResponse map(Duration market);

    Duration map(DurationResponse market);
}
