package com.upk.diploma.service.order;

import com.upk.diploma.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse getById(Long id);

    List<OrderResponse> getAll();

    List<OrderResponse> getAllForParticularCustomer(Long customerProfileId);

    OrderResponse create(OrderResponse orderResponse);

    OrderResponse update(Long orderId, OrderResponse orderResponse);

    void delete(Long orderId);
}
