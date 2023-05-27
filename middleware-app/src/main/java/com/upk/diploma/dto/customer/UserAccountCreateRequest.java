package com.upk.diploma.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserAccountCreateRequest {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Pattern(regexp = "^\\+[0-9]{11}", message = "Must be in this format: '+79112223344'.")
    private String phone;

    private RoleResponse role;
}
