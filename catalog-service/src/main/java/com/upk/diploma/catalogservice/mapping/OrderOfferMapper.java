package com.upk.diploma.catalogservice.mapping;

import com.upk.diploma.catalogservice.dto.OrderOfferResponse;
import com.upk.diploma.catalogservice.model.entity.OrderOffer;
import com.upk.diploma.catalogservice.repository.OfferRepository;
import com.upk.diploma.catalogservice.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrderOfferMapper {

    @Autowired
    protected OfferRepository offerRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Mapping(source = "orderOffer.order", target = "orderId")
    @Mapping(source = "orderOffer.offer.id", target = "offerId")
    public abstract OrderOfferResponse map(OrderOffer orderOffer);

    public OrderOffer map(OrderOfferResponse orderOffer) {
        if (orderOffer == null) {
            return null;
        }

        OrderOffer.OrderOfferBuilder orderOfferBuilder = OrderOffer.builder();

        orderOfferBuilder.id(orderOffer.getId());
        orderOfferBuilder.offer(offerRepository.findById(orderOffer.getOfferId())
                .orElseThrow(() -> new com.upk.diploma.catalogservice.exception.DataNotFoundException("Offer", orderOffer.getOfferId())));
        orderOfferBuilder.order(orderOffer.getOrderId());
        orderOfferBuilder.quantity(orderOffer.getQuantity());

        return orderOfferBuilder.build();
    }
}
