package com.upk.diploma.controller.customer;


import com.upk.diploma.config.security.jwt.JwtUtils;
import com.upk.diploma.config.security.service.UserDetails;
import com.upk.diploma.dto.MessageDTO;
import com.upk.diploma.dto.customer.JwtDTO;
import com.upk.diploma.dto.customer.LoginDTO;
import com.upk.diploma.dto.customer.RoleResponse;
import com.upk.diploma.dto.customer.UserAccountCreateRequest;
import com.upk.diploma.exception.RegisterUserException;
import com.upk.diploma.service.customer.UserAccountService;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(PublicUserApi.PUBLIC_USER_API_PATH)
@RequiredArgsConstructor
public class PublicUserApi {
    public static final String PUBLIC_USER_API_PATH = "/api/public/user";

    private final UserAccountService userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getAllRolesForUsers() {
        final List<RoleResponse> allRoles = userAccountService.getAllRoles();

        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtDTO> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList().get(0);

        return ResponseEntity.ok(new JwtDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserAccountCreateRequest signUpRequest)
            throws RegisterUserException, AuthException {
        userAccountService.register(signUpRequest);
        return ResponseEntity.ok(new MessageDTO("User account " + signUpRequest.getUsername() + " has been registered successfully"));
    }
}
