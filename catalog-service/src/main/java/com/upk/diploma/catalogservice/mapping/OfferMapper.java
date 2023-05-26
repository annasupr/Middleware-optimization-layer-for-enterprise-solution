package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.OfferResponse;
import com.upk.diploma.catalogservice.model.entity.Offer;
import com.upk.diploma.catalogservice.repository.MarketRepository;
import com.upk.diploma.catalogservice.repository.PointOfDistributionRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {MarketMapper.class, PointOfDistributionMapper.class})
public abstract class OfferMapper {

    @Autowired
    protected MarketRepository marketRepository;

    @Autowired
    protected PointOfDistributionRepository pointOfDistributionRepository;

    public abstract OfferResponse map(Offer offer);

    public Offer map(OfferResponse offer){
        if ( offer == null ) {
            return null;
        }

        Offer.OfferBuilder offer1 = Offer.builder();

        offer1.id( offer.getId() );
        offer1.title( offer.getTitle() );
        offer1.market( marketRepository.findById(offer.getMarket().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Market", offer.getMarket().getId())) );
        offer1.pointOfDistribution( pointOfDistributionRepository.findById(offer.getPointOfDistribution().getId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Point of distribution", offer.getPointOfDistribution().getId())) );

        return offer1.build();
    }
}
