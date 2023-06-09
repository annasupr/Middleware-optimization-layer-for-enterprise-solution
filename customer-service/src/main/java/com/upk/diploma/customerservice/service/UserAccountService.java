package com.upk.diploma.customerservice.service;

import com.upk.diploma.customerservice.dto.UserAccountCreateRequest;
import com.upk.diploma.customerservice.dto.UserAccountResponse;

public interface UserAccountService {
    UserAccountResponse getById(Long id);

    UserAccountResponse getByUsername(String username);

    UserAccountResponse create(UserAccountCreateRequest userAccountResponse);

    UserAccountResponse update(Long userAccountId, UserAccountCreateRequest userAccountResponse);

    void delete(Long userAccountId);

    Boolean verifyUsernameExists(String username);

    Boolean verifyEmailExists(String email);
}
