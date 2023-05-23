package com.upk.diploma.customerservice.service;

import com.upk.diploma.customerservice.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse getRoleById(Long id);

    List<RoleResponse> getAll();
}
