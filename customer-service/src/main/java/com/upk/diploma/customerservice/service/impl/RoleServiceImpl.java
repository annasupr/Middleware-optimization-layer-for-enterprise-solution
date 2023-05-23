package com.upk.diploma.customerservice.service.impl;

import com.upk.diploma.customerservice.dto.RoleResponse;
import com.upk.diploma.customerservice.exception.DataNotFoundException;
import com.upk.diploma.customerservice.mapping.RoleMapper;
import com.upk.diploma.customerservice.repository.RoleRepository;
import com.upk.diploma.customerservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::map)
                .orElseThrow(() -> new DataNotFoundException("Role", id));
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream().map(roleMapper::map)
                .collect(Collectors.toList());
    }
}
