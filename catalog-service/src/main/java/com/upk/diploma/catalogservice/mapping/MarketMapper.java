package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.MarketResponse;
import com.upk.diploma.catalogservice.model.entity.Market;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketMapper {
    MarketResponse map(Market market);

    Market map(MarketResponse market);
}
