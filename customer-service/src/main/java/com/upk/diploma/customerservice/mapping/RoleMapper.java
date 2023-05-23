package com.upk.diploma.customerservice.mapping;

import com.upk.diploma.customerservice.dto.RoleResponse;
import com.upk.diploma.customerservice.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role map(RoleResponse roleResponse);

    RoleResponse map(Role role);
}
