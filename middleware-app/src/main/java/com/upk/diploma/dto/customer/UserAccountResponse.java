package com.upk.diploma.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserAccountResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private RoleResponse role;
}
