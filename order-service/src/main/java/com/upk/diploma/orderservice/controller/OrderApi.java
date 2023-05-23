package com.upk.diploma.orderservice.controller;

import com.upk.diploma.orderservice.dto.OrderResponse;
import com.upk.diploma.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(OrderApi.ORDERS_API_PATH)
@RequiredArgsConstructor
public class OrderApi {
    public static final String ORDERS_API_PATH = "/api/orders";

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable final Long orderId) {
        final OrderResponse foundOrder = orderService.getById(orderId);
        return new ResponseEntity<>(foundOrder, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        final List<OrderResponse> allOrders = orderService.getAll();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerProfileId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForCustomer(@PathVariable final Long customerProfileId) {
        final List<OrderResponse> ordersForCustomer = orderService.getAllForParticularCustomer(customerProfileId);
        return new ResponseEntity<>(ordersForCustomer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody final OrderResponse orderCreateRequest) {
        final OrderResponse createdOrder = orderService.create(orderCreateRequest);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable final Long orderId,
            @RequestBody final OrderResponse orderUpdateRequest
    ) {
        final OrderResponse updatedOrder = orderService.update(orderId, orderUpdateRequest);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable final Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
