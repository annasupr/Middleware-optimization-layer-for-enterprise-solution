package com.upk.diploma.service.customer.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upk.diploma.config.properties.ExternalServicesProperties;
import com.upk.diploma.dto.customer.RoleResponse;
import com.upk.diploma.dto.customer.UserAccountCreateRequest;
import com.upk.diploma.dto.customer.UserAccountResponse;
import com.upk.diploma.exception.RegisterUserException;
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

    public static final String ERROR_USERNAME = "Username is already taken";
    public static final String ERROR_EMAIL = "Email is already in use";
    public static final String REGISTRATION_NOT_EMPTY_BODY = "Username, email and password are necessary and must not be empty";
    public static final String REGISTRATION_WRONG_BODY = "Please, verify that all fields are correct";

    public UserAccountServiceImpl(ExternalServicesProperties externalServicesProperties,
                                  RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(externalServicesProperties, restTemplate, objectMapper, UserAccountResponse.class);
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
    public UserAccountResponse register(UserAccountCreateRequest userAccountCreateRequest) throws RegisterUserException {
        validateSignUpRequest(userAccountCreateRequest);
        HttpHeaders headers = new HttpHeaders();

        return super.post(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH,
                userAccountCreateRequest,
                headers);
    }

    @Override
    public UserAccountResponse getInfoByUsername(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("username", username);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return super.get(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH,
                headers);
    }

    @Override
    public UserAccountResponse getInfoById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return super.get(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH + "/" + id,
                headers);
    }

    private void validateSignUpRequest(UserAccountCreateRequest signUpRequest) throws RegisterUserException {
        if (signUpRequest.getUsername().equals("")
                || signUpRequest.getPassword().equals("")
                || signUpRequest.getEmail().equals("")){
            throw new RegisterUserException(REGISTRATION_NOT_EMPTY_BODY);
        }

        if (checkUsername(signUpRequest.getUsername())) {
            throw new RegisterUserException(ERROR_USERNAME);
        }

        if (checkEmail(signUpRequest.getEmail())) {
            throw new RegisterUserException(ERROR_EMAIL);
        }
    }

    private Boolean checkUsername( final String usernameToCheck){
        HttpHeaders headers = new HttpHeaders();
        headers.set("username", usernameToCheck);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Boolean> response = restTemplate
                .exchange(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH + "/username",
                        HttpMethod.GET,requestEntity,
                        Boolean.class);

        return response.getBody();
    }

    private Boolean checkEmail( final String emailToCheck){
        HttpHeaders headers = new HttpHeaders();
        headers.set("email", emailToCheck);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<Boolean> response = restTemplate
                .exchange(externalServicesProperties.getCustomerServiceUrl() + USER_ACCOUNT_API_PATH + "/email",
                        HttpMethod.GET,requestEntity,
                        Boolean.class);

        return response.getBody();
    }
}
