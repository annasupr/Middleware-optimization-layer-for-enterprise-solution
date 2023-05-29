package com.upk.diploma.controller.order;

import com.upk.diploma.dto.order.OrderResponse;
import com.upk.diploma.service.order.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
@RequestMapping(SecuredOrderApi.ORDERS_API_PATH)
@RequiredArgsConstructor
public class SecuredOrderApi {

    public static final String ORDERS_API_PATH = "/api/secured/orders";
    private static final String CIRCUIT_BREAKER_NAME = "orderService";
    private static final String SERVICE_UNAVAILABLE_ERROR = "Downstream service is unavailable. Operation is not supported currently.";

    private final OrderService orderService;

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getByIdFallback")
    @RateLimiter(name = CIRCUIT_BREAKER_NAME)
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable final Long orderId) {
        final OrderResponse foundOrder = orderService.getById(orderId);
        return new ResponseEntity<>(foundOrder, HttpStatus.OK);
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getAllOrdersFallback")
    @RateLimiter(name = CIRCUIT_BREAKER_NAME)
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        final List<OrderResponse> allOrders = orderService.getAll();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getAllOrdersForCustomerFallback")
    @RateLimiter(name = CIRCUIT_BREAKER_NAME)
    @GetMapping("/customer/{customerProfileId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersForCustomer(@PathVariable final Long customerProfileId) {
        final List<OrderResponse> ordersForCustomer = orderService.getAllForParticularCustomer(customerProfileId);
        return new ResponseEntity<>(ordersForCustomer, HttpStatus.OK);
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "createOrderFallback")
    @RateLimiter(name = CIRCUIT_BREAKER_NAME)
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody final OrderResponse orderCreateRequest) {
        final OrderResponse createdOrder = orderService.create(orderCreateRequest);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "updateOrderFallback")
    @RateLimiter(name = CIRCUIT_BREAKER_NAME)
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateOrder(
            @PathVariable final Long orderId,
            @RequestBody final OrderResponse orderUpdateRequest
    ) {
        final OrderResponse updatedOrder = orderService.update(orderId, orderUpdateRequest);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "deleteOrderFallback")
    @RateLimiter(name = CIRCUIT_BREAKER_NAME)
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable final Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> getByIdFallback(Long id, Exception exc){
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<String> getAllOrdersFallback(Exception exc){
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<String> getAllOrdersForCustomerFallback(Long id, Exception exc){
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<String> createOrderFallback(Long id, Exception exc){
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<String> updateOrderFallback(Long id, Exception exc){
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public ResponseEntity<String> deleteOrderFallback(Long id, Exception exc){
        return new ResponseEntity<>(SERVICE_UNAVAILABLE_ERROR, HttpStatus.SERVICE_UNAVAILABLE);
    }
}

