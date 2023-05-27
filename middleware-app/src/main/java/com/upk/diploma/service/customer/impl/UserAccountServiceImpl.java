package com.upk.diploma.service.customer.impl;

import com.upk.diploma.config.properties.ExternalServicesProperties;
import com.upk.diploma.dto.customer.RoleResponse;
import com.upk.diploma.dto.customer.UserAccountCreateRequest;
import com.upk.diploma.dto.customer.UserAccountResponse;
import com.upk.diploma.service.BasicRestService;
import com.upk.diploma.service.customer.UserAccountService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserAccountServiceImpl extends BasicRestService<UserAccountResponse> implements UserAccountService {

    private static final String USER_ACCOUNT_API_PATH = "/api/account";
    private static final String USER_ACCOUNT_ROLES_API_PATH = "/api/roles";

    public UserAccountServiceImpl(ExternalServicesProperties externalServicesProperties,
                                  RestTemplate restTemplate) {
        super(externalServicesProperties, restTemplate);
    }

    @Override
    public List<RoleResponse> getAllRoles() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<List<RoleResponse>> response = restTemplate
                .exchange(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_ROLES_API_PATH, HttpMethod.GET,requestEntity,
                        new ParameterizedTypeReference<>() {
                        });

        return response.getBody();
    }

    @Override
    public UserAccountResponse register(UserAccountCreateRequest userAccountCreateRequest) {
        return null;
    }

    @Override
    public UserAccountResponse getInfoByUsername(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return super.get(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH + "/" + username,
                headers);
    }

    @Override
    public UserAccountResponse getInfoById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return super.get(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH + "/" + id,
                headers);
    }

    //register
}
