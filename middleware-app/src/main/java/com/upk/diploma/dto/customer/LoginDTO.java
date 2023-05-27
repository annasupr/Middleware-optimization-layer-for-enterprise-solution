package com.upk.diploma.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
