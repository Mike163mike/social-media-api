package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.RoleDto;
import com.effectivemobile.socialmediaapi.model.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleDto map(Role role);

    Role map(RoleDto roleDto);
}
