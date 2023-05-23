package com.upk.diploma.customerservice.service.impl;

import com.upk.diploma.customerservice.dto.CustomerProfileResponse;
import com.upk.diploma.customerservice.exception.ConversionException;
import com.upk.diploma.customerservice.exception.DataNotFoundException;
import com.upk.diploma.customerservice.mapping.CustomerProfileMapper;
import com.upk.diploma.customerservice.model.entity.CustomerProfile;
import com.upk.diploma.customerservice.repository.CustomerProfileRepository;
import com.upk.diploma.customerservice.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileMapper customerProfileMapper;

    @Override
    public CustomerProfileResponse getById(Long id) {
        return customerProfileRepository.findById(id)
                .map(customerProfileMapper::map)
                .orElseThrow(() -> new DataNotFoundException("Customer Profile", id));
    }

    @Override
    public CustomerProfileResponse create(CustomerProfileResponse customerProfileResponse) {
        final CustomerProfile customerProfile = Optional.ofNullable(customerProfileMapper.map(customerProfileResponse))
                .orElseThrow(() -> new ConversionException(("Customer Profile")));

        final CustomerProfile savedCustomerProfile = customerProfileRepository.save(customerProfile);

        return customerProfileMapper.map(savedCustomerProfile);
    }

    @Override
    public CustomerProfileResponse update(Long customerProfileId, CustomerProfileResponse customerProfileResponse) {
        final CustomerProfile customerProfile = Optional.ofNullable(customerProfileMapper.map(customerProfileResponse))
                .orElseThrow(() -> new ConversionException("Customer Profile"));

        customerProfile.setId(customerProfileId);

        final CustomerProfile savedCustomerProfile = customerProfileRepository.save(customerProfile);
        return customerProfileMapper.map(savedCustomerProfile);
    }

    @Override
    public void delete(Long customerProfileId) {
        try {
            customerProfileRepository.deleteById(customerProfileId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("Customer Profile", customerProfileId);
        }
    }
}
