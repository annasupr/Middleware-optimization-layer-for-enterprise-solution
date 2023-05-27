package com.upk.diploma.service.customer;

import com.upk.diploma.dto.customer.RoleResponse;
import com.upk.diploma.dto.customer.UserAccountCreateRequest;
import com.upk.diploma.dto.customer.UserAccountResponse;

import java.util.List;

public interface UserAccountService {
    List<RoleResponse> getAllRoles();

    UserAccountResponse register(UserAccountCreateRequest userAccountCreateRequest);

    UserAccountResponse getInfoByUsername(String username);

    UserAccountResponse getInfoById(Long id);
}
