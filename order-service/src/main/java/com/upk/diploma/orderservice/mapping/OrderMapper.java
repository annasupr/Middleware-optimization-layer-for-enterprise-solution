package com.upk.diploma.orderservice.mapping;

import com.upk.diploma.orderservice.dto.OrderResponse;
import com.upk.diploma.orderservice.dto.OrderStatusResponse;
import com.upk.diploma.orderservice.model.entity.Order;
import com.upk.diploma.orderservice.model.entity.OrderStatus;
import com.upk.diploma.orderservice.repository.OrderStatusRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    protected OrderStatusRepository orderStatusRepository;

    public Order map(OrderResponse orderResponse) {
        if (orderResponse == null) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id(orderResponse.getId());
        order.dateOrdered(orderResponse.getDateOrdered());
        order.dateReceived(orderResponse.getDateReceived());
        order.customerProfileId(orderResponse.getCustomerProfileId());
        order.orderStatus(orderStatusRepository.findById(orderResponse.getOrderStatus().getId())
                .orElseThrow(() -> new com.upk.diploma.orderservice.exception.DataNotFoundException("Order Status", orderResponse.getOrderStatus().getId())));

        return order.build();
    }

    public abstract OrderResponse map(Order order);

    public abstract OrderStatusResponse map(OrderStatus order);

    public abstract OrderStatus map(OrderStatusResponse order);
}
