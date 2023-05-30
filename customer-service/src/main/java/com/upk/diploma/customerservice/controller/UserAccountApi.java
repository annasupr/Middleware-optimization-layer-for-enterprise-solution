package com.upk.diploma.customerservice.controller;

import com.upk.diploma.customerservice.dto.UserAccountCreateRequest;
import com.upk.diploma.customerservice.dto.UserAccountResponse;
import com.upk.diploma.customerservice.service.UserAccountService;
import com.upk.diploma.customerservice.util.SpanUtils;
import io.opencensus.common.Scope;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserAccountApi.USER_ACCOUNT_API_PATH)
@RequiredArgsConstructor
public class UserAccountApi {
    public static final String USER_ACCOUNT_API_PATH = "/api/account";

    private final UserAccountService userAccountService;

    private static final Tracer tracer = Tracing.getTracer();

    @GetMapping("/{userAccountId}")
    public final ResponseEntity<UserAccountResponse> getUserAccountById(@PathVariable final Long userAccountId) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi getUserAccountById").startSpan();
        UserAccountResponse foundUserAccount = null;
        try (Scope ws = tracer.withSpan(span)) {
            foundUserAccount = userAccountService.getById(userAccountId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(foundUserAccount, HttpStatus.OK);
    }

    @GetMapping
    public final ResponseEntity<UserAccountResponse> getUserAccountByUsername(@RequestHeader final String username) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi getUserAccountByUsername").startSpan();
        UserAccountResponse foundUserAccount = null;
        try (Scope ws = tracer.withSpan(span)) {
            foundUserAccount = userAccountService.getByUsername(username);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(foundUserAccount, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public final ResponseEntity<UserAccountResponse> createUserAccount(
            @RequestBody final UserAccountCreateRequest userAccountCreateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi createUserAccount").startSpan();
        UserAccountResponse createdUserAccount = null;
        try (Scope ws = tracer.withSpan(span)) {
            createdUserAccount = userAccountService.create(userAccountCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(createdUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{userAccountId}")
    public final ResponseEntity<UserAccountResponse> updateUserAccount(
            @PathVariable final Long userAccountId,
            @RequestBody final UserAccountCreateRequest userAccountCreateRequest
    ) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi updateUserAccount").startSpan();
        UserAccountResponse updatedUserAccount = null;
        try (Scope ws = tracer.withSpan(span)) {
            updatedUserAccount = userAccountService.update(userAccountId, userAccountCreateRequest);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(updatedUserAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{userAccountId}")
    public final ResponseEntity<Void> deleteUserAccount(@PathVariable final Long userAccountId) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi deleteUserAccount").startSpan();
        try (Scope ws = tracer.withSpan(span)) {
            userAccountService.delete(userAccountId);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/username")
    public final ResponseEntity<Boolean> checkIfUsernameExists(@RequestHeader final String username) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi checkIfUsernameExists").startSpan();
        Boolean usernameExists = null;
        try (Scope ws = tracer.withSpan(span)) {
            usernameExists = userAccountService.verifyUsernameExists(username);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(usernameExists, HttpStatus.OK);
    }

    @GetMapping("/email")
    public final ResponseEntity<Boolean> checkIfEmailExists(@RequestHeader final String email) {
        Span span = SpanUtils.buildSpan(tracer, "UserAccountApi checkIfEmailExists").startSpan();
        Boolean emailExists = null;
        try (Scope ws = tracer.withSpan(span)) {
            emailExists = userAccountService.verifyEmailExists(email);
        } finally {
            span.end();
        }
        return new ResponseEntity<>(emailExists, HttpStatus.OK);
    }
}
