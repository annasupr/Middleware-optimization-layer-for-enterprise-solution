package com.upk.diploma.customerservice.mapping;

import com.upk.diploma.customerservice.dto.UserAccountCreateRequest;
import com.upk.diploma.customerservice.dto.UserAccountResponse;
import com.upk.diploma.customerservice.exception.DataNotFoundException;
import com.upk.diploma.customerservice.model.entity.UserAccount;
import com.upk.diploma.customerservice.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserAccountMapper {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected RoleMapper roleMapper;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public UserAccount map(UserAccountResponse userAccountResponse) {
        if ( userAccountResponse == null ) {
            return null;
        }

        UserAccount.UserAccountBuilder userAccount = UserAccount.builder();

        userAccount.id( userAccountResponse.getId() );
        userAccount.username( userAccountResponse.getUsername() );
        userAccount.email( userAccountResponse.getEmail() );
        userAccount.phone( userAccountResponse.getPhone() );
        userAccount.role( roleRepository.findById(userAccountResponse.getRole().getId())
                .orElseThrow(() -> new DataNotFoundException("Role", userAccountResponse.getRole().getId())) );

        return userAccount.build();
    }

    public UserAccount map(UserAccountCreateRequest userAccountCreateRequest) {
        if ( userAccountCreateRequest == null ) {
            return null;
        }

        UserAccount.UserAccountBuilder userAccount = UserAccount.builder();

        userAccount.id( userAccountCreateRequest.getId() );
        userAccount.username( userAccountCreateRequest.getUsername() );
        userAccount.password(userAccountCreateRequest.getPassword() );
        userAccount.email( userAccountCreateRequest.getEmail() );
        userAccount.phone( userAccountCreateRequest.getPhone() );
        userAccount.role( roleRepository.findById(userAccountCreateRequest.getRole().getId())
                .orElseThrow(() -> new DataNotFoundException("Role", userAccountCreateRequest.getRole().getId())) );

        return userAccount.build();
    }

    public abstract UserAccountResponse map(UserAccount userAccount);
}
