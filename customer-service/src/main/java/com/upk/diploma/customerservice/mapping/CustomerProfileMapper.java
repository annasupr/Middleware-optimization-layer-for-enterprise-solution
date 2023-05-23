package com.upk.diploma.customerservice.mapping;

import com.upk.diploma.customerservice.dto.CustomerProfileResponse;
import com.upk.diploma.customerservice.exception.DataNotFoundException;
import com.upk.diploma.customerservice.model.entity.CustomerProfile;
import com.upk.diploma.customerservice.repository.UserAccountRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CustomerProfileMapper {

        @Autowired
        protected UserAccountRepository userAccountRepository;

        @Autowired
        protected UserAccountMapper userAccountMapper;

        public CustomerProfile map(CustomerProfileResponse customerProfileResponse) {
                if ( customerProfileResponse == null ) {
                        return null;
                }

                CustomerProfile.CustomerProfileBuilder customerProfile = CustomerProfile.builder();

                customerProfile.id( customerProfileResponse.getId() );
                customerProfile.firstName( customerProfileResponse.getFirstName() );
                customerProfile.middleName( customerProfileResponse.getMiddleName() );
                customerProfile.lastName( customerProfileResponse.getLastName() );
                customerProfile.dateOfBirth( customerProfileResponse.getDateOfBirth() );
                customerProfile.userAccount( userAccountRepository.findById(customerProfileResponse.getUserAccount().getId())
                        .orElseThrow(() -> new DataNotFoundException("User Account", customerProfileResponse.getUserAccount().getId())) );

                return customerProfile.build();
        }

         public abstract CustomerProfileResponse map(CustomerProfile customerProfile);
}
