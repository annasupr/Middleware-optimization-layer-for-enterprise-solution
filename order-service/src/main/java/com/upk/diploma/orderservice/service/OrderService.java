package com.upk.diploma.orderservice.service;

import com.upk.diploma.orderservice.dto.OrderResponse;
import com.upk.diploma.orderservice.dto.OrderStatusResponse;

import java.util.List;

public interface OrderService {

    OrderResponse getById(Long id);

    List<OrderResponse> getAll();

    List<OrderResponse> getAllForParticularCustomer(Long customerProfileId);

    List<OrderStatusResponse> getAllOrderStatuses();

    OrderResponse create(OrderResponse orderResponse);

    OrderResponse update(Long orderId, OrderResponse orderResponse);

    void delete(Long orderId);
}
