package com.upk.diploma.orderservice.service.impl;

import com.upk.diploma.orderservice.dto.OrderResponse;
import com.upk.diploma.orderservice.dto.OrderStatusResponse;
import com.upk.diploma.orderservice.exception.ConversionException;
import com.upk.diploma.orderservice.exception.DataNotFoundException;
import com.upk.diploma.orderservice.mapping.OrderMapper;
import com.upk.diploma.orderservice.model.entity.Order;
import com.upk.diploma.orderservice.repository.OrderRepository;
import com.upk.diploma.orderservice.repository.OrderStatusRepository;
import com.upk.diploma.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse getById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::map)
                .orElseThrow(() -> new DataNotFoundException("Order", id));
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderRepository.findAll()
                .stream().map(orderMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponse> getAllForParticularCustomer(Long customerProfileId) {
        return orderRepository.findAllByCustomerProfileId(customerProfileId)
                .stream().map(orderMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderStatusResponse> getAllOrderStatuses() {
        return orderStatusRepository.findAll()
                .stream().map(orderMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse create(OrderResponse orderResponse) {
        final Order order = orderMapper.map(orderResponse);
        final Order savedOrder = orderRepository.save(order);
        return orderMapper.map(savedOrder);
    }

    @Override
    public OrderResponse update(Long orderId, OrderResponse orderResponse) {
        final Order order = Optional.ofNullable(orderMapper.map(orderResponse))
                .orElseThrow(() -> new ConversionException("User Account"));
        order.setId(orderId);

        final Order savedUserAccount = orderRepository.save(order);
        return orderMapper.map(savedUserAccount);
    }

    @Override
    public void delete(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Order", orderId);
        }
    }
}
