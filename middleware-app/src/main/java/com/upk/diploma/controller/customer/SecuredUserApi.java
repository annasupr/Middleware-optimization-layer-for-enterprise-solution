package com.upk.diploma.controller.customer;

import com.upk.diploma.dto.customer.UserAccountResponse;
import com.upk.diploma.service.customer.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SecuredUserApi.SECURED_USER_API_PATH)
@RequiredArgsConstructor
public class SecuredUserApi {
    public static final String SECURED_USER_API_PATH = "/api/secured/user";

    private final UserAccountService userAccountService;

    @GetMapping
    public ResponseEntity<List<UserAccountResponse>> getAllUserAccounts() {
        List<UserAccountResponse> userAccounts = userAccountService.getAllUserAccounts();

        return new ResponseEntity<>(userAccounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccountResponse> getUserAccountById(@PathVariable Long id) {
        UserAccountResponse userAccount = userAccountService.getUserAccountById(id);

        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccountById(@PathVariable Long id) {
        userAccountService.deleteUserAccountById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponse> updateUserAccount(@PathVariable Long id, @Valid @RequestBody UserAccountResponse updatedUserAccount)
    {
        UserAccountResponse userAccount = userAccountService.updateUserAccount(id, updatedUserAccount);

        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }
}
