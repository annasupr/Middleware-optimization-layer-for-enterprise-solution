package com.upk.diploma.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerProfileResponse {
    private Long id;
    private UserAccountResponse userAccount;
    private String firstName;
    private String middleName;
    private String lastName;
    private OffsetDateTime dateOfBirth;
    private Boolean sex;
}
