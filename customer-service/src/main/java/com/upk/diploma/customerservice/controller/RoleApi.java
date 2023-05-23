package com.upk.diploma.customerservice.controller;

import com.upk.diploma.customerservice.dto.RoleResponse;
import com.upk.diploma.customerservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RoleApi.ROLES_API_PATH)
@RequiredArgsConstructor
public class RoleApi {
    public static final String ROLES_API_PATH = "/api/roles";

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRolesForUsers() {
        final List<RoleResponse> allDealCategories = roleService.getAll();

        return new ResponseEntity<>(allDealCategories, HttpStatus.OK);
    }
}
