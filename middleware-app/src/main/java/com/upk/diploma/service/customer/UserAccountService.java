package com.upk.diploma.service.customer;

import com.upk.diploma.dto.customer.RoleResponse;
import com.upk.diploma.dto.customer.UserAccountCreateRequest;
import com.upk.diploma.dto.customer.UserAccountResponse;
import com.upk.diploma.exception.RegisterUserException;

import java.util.List;

public interface UserAccountService {
    List<RoleResponse> getAllRoles();

    UserAccountResponse register(UserAccountCreateRequest userAccountCreateRequest) throws RegisterUserException;

    UserAccountResponse getInfoByUsername(String username);

    UserAccountResponse getInfoById(Long id);

    List<UserAccountResponse> getAllUserAccounts();

    UserAccountResponse getUserAccountById(Long id);

    void deleteUserAccountById(Long id);

    UserAccountResponse updateUserAccount(Long id, UserAccountResponse updatedUserAccount);
}
