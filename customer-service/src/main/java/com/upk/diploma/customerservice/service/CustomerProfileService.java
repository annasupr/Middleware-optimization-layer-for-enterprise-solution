package com.upk.diploma.customerservice.service;

import com.upk.diploma.customerservice.dto.CustomerProfileResponse;

public interface CustomerProfileService {
    CustomerProfileResponse getById(Long id);

    CustomerProfileResponse create(CustomerProfileResponse customerProfileResponse);

    CustomerProfileResponse update(Long customerProfileId, CustomerProfileResponse customerProfileResponse);

    void delete(Long customerProfileId);
}
