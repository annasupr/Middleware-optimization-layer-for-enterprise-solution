package com.upk.diploma.customerservice.controller;

import com.upk.diploma.customerservice.dto.UserAccountCreateRequest;
import com.upk.diploma.customerservice.dto.UserAccountResponse;
import com.upk.diploma.customerservice.service.UserAccountService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserAccountApi.USER_ACCOUNT_API_PATH)
@RequiredArgsConstructor
public class UserAccountApi {
    public static final String USER_ACCOUNT_API_PATH = "/api/account";

    private final UserAccountService userAccountService;

    @GetMapping("/{userAccountId}")
    public final ResponseEntity<UserAccountResponse> getUserAccountById(@PathVariable final Long userAccountId) {
        final UserAccountResponse foundUserAccount = userAccountService.getById(userAccountId);

        return new ResponseEntity<>(foundUserAccount, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public final ResponseEntity<UserAccountResponse> createUserAccount(
            @RequestBody final UserAccountCreateRequest userAccountCreateRequest
    ) {
        final UserAccountResponse createdUserAccount = userAccountService.create(userAccountCreateRequest);

        return new ResponseEntity<>(createdUserAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{userAccountId}")
    public final ResponseEntity<UserAccountResponse> updateUserAccount( @PathVariable final Long userAccountId,
            @RequestBody final UserAccountCreateRequest userAccountCreateRequest
    ) {
        final UserAccountResponse createdUserAccount = userAccountService.update(userAccountId, userAccountCreateRequest);

        return new ResponseEntity<>(createdUserAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userAccountId}")
    public final ResponseEntity<Void> updateUserAccount( @PathVariable final Long userAccountId) {
        userAccountService.delete(userAccountId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
