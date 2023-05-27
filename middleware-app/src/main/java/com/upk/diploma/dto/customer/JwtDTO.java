package com.upk.diploma.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDTO {
    private String accessToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String role;

    public JwtDTO(String jwt, Long id, String username, String email, String role) {
        this.accessToken = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

}
