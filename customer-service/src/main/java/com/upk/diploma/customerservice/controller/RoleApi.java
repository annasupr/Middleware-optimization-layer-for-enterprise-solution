package com.upk.diploma.customerservice.controller;

import com.upk.diploma.customerservice.dto.RoleResponse;
import com.upk.diploma.customerservice.service.RoleService;
import com.upk.diploma.customerservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
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

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRolesForUsers() {
        Span span = SpanUtils.buildSpan(tracer, "RoleApi getAllRolesForUsers").startSpan();
        List<RoleResponse> allRoles = null;
        try (Scope ws = tracer.withSpan(span)) {
            allRoles = roleService.getAll();
        } finally {
            span.end();
        }
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }
}
