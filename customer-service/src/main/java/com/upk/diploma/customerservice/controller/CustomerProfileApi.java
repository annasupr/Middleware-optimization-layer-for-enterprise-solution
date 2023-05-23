package com.upk.diploma.customerservice.controller;

import com.upk.diploma.customerservice.dto.CustomerProfileResponse;
import com.upk.diploma.customerservice.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerProfileApi.CUSTOMER_PROFILE_API_PATH)
@RequiredArgsConstructor
public class CustomerProfileApi {
    public static final String CUSTOMER_PROFILE_API_PATH = "/api/customer-profile";

    private final CustomerProfileService customerProfileService;

    @GetMapping("/{customerProfileId}")
    public ResponseEntity<CustomerProfileResponse> getCustomerProfileById(@PathVariable final Long customerProfileId) {
        final CustomerProfileResponse foundCustomerProfile = customerProfileService.getById(customerProfileId);

        return new ResponseEntity<>(foundCustomerProfile, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerProfileResponse> createCustomerProfile(
            @RequestBody final CustomerProfileResponse customerProfileCreateRequest
    ) {
        final CustomerProfileResponse createdCustomerProfile = customerProfileService.create(customerProfileCreateRequest);

        return new ResponseEntity<>(createdCustomerProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{customerProfileId}")
    public ResponseEntity<CustomerProfileResponse> updateCustomerProfile(
            @PathVariable final Long customerProfileId,
            @RequestBody final CustomerProfileResponse customerProfileCreateRequest
    ) {
        final CustomerProfileResponse updatedCustomerProfile = customerProfileService.update(customerProfileId, customerProfileCreateRequest);

        return new ResponseEntity<>(updatedCustomerProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{customerProfileId}")
    public ResponseEntity<Void> deleteCustomerProfile(@PathVariable final Long customerProfileId) {
        customerProfileService.delete(customerProfileId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
