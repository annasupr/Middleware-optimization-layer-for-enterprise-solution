package com.upk.diploma.config.security.service;

import com.upk.diploma.dto.customer.UserAccountResponse;
import com.upk.diploma.service.customer.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountResponse user = userAccountService.getInfoByUsername(username);

        return com.upk.diploma.config.security.service.UserDetails .build(user);
    }
}
