package com.upk.diploma.service.order.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upk.diploma.config.properties.ExternalServicesProperties;
import com.upk.diploma.dto.order.OrderResponse;
import com.upk.diploma.service.BasicRestService;
import com.upk.diploma.service.order.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderServiceImpl extends BasicRestService<OrderResponse> implements OrderService {

    private static final String ORDER_API_PATH = "/api/orders";
    private static final String ORDER_API_BY_ID_PATH = "/api/orders/{id}";
    private static final String ORDER_API_BY_CUSTOMER_PATH = "/api/orders/customer/{id}";

    public OrderServiceImpl(ExternalServicesProperties externalServicesProperties,
                                  RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(externalServicesProperties, restTemplate, objectMapper, OrderResponse.class);
    }

    @Override
    public OrderResponse getById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return super.get(externalServicesProperties.getOrderServiceUrl() + ORDER_API_BY_ID_PATH.replace("{id}", id.toString()),
                headers);
    }

    @Override
    public List<OrderResponse> getAll() {
        String url = externalServicesProperties.getOrderServiceUrl() + ORDER_API_PATH;
        return getAll(url, null);
    }

    @Override
    public List<OrderResponse> getAllForParticularCustomer(Long customerProfileId) {
        String url = externalServicesProperties.getOrderServiceUrl()
                + ORDER_API_BY_CUSTOMER_PATH.replace("{id}", customerProfileId.toString());
        return getAll(url, null);
    }

    @Override
    public OrderResponse create(OrderResponse orderResponse) {
        String url = externalServicesProperties.getOrderServiceUrl() + ORDER_API_PATH;
        HttpHeaders headers = createHeaders();
        return post(url, orderResponse, headers);
    }

    @Override
    public OrderResponse update(Long orderId, OrderResponse orderResponse) {
        String url = externalServicesProperties.getOrderServiceUrl()
                + ORDER_API_BY_ID_PATH.replace("{id}", orderId.toString());
        HttpHeaders headers = createHeaders();
        return put(url, orderResponse, headers);
    }

    @Override
    public void delete(Long orderId) {
        String url = externalServicesProperties.getOrderServiceUrl()
                + ORDER_API_BY_ID_PATH.replace("{id}", orderId.toString());
        HttpHeaders headers = createHeaders();
        delete(url, headers);
    }
}
