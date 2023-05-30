package com.upk.diploma.orderservice.controller;

import com.upk.diploma.orderservice.dto.OrderResponse;
import com.upk.diploma.orderservice.service.OrderService;
import com.upk.diploma.orderservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(OrderApi.ORDERS_API_PATH)
@RequiredArgsConstructor
public class OrderApi {
    public static final String ORDERS_API_PATH = "/api/orders";

    private final OrderService orderService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable final Long orderId) {
        Span span = SpanUtils.buildSpan(tracer, "OrderApi getOrderById").startSpan();
        OrderResponse foundOrder = null;
        try (Scope ws = tracer.withSpan(span)) {
            foundOrder = orderService.getById(orderId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(foundOrder, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        Span span = SpanUtils.buildSpan(tracer, "OrderApi getAllOrders").startSpan();
        List<OrderResponse> allOrders = null;
        try (Scope ws = tracer.withSpan(span)) {

            allOrders = orderService.getAll();
        } finally {
            span.end();
        }

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerProfileId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForCustomer(@PathVariable final Long customerProfileId) {
        Span span = SpanUtils.buildSpan(tracer, "OrderApi getAllOrdersForCustomer").startSpan();
        List<OrderResponse> ordersForCustomer = null;
        try (Scope ws = tracer.withSpan(span)) {
            ordersForCustomer = orderService.getAllForParticularCustomer(customerProfileId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(ordersForCustomer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody final OrderResponse orderCreateRequest) {
        Span span = SpanUtils.buildSpan(tracer, "OrderApi createOrder").startSpan();
        OrderResponse createdOrder = null;
        try (Scope ws = tracer.withSpan(span)) {
            createdOrder = orderService.create(orderCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable final Long orderId,
            @RequestBody final OrderResponse orderUpdateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "OrderApi updateOrder").startSpan();
        OrderResponse updatedOrder = null;
        try (Scope ws = tracer.withSpan(span)) {
            updatedOrder = orderService.update(orderId, orderUpdateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable final Long orderId) {
        Span span = SpanUtils.buildSpan(tracer, "OrderApi deleteOrder").startSpan();
        try (Scope ws = tracer.withSpan(span)) {
            orderService.delete(orderId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
