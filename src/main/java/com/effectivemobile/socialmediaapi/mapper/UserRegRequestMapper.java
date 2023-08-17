package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.UserRegRequestDto;
import com.effectivemobile.socialmediaapi.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserRegRequestMapper {

    UserRegRequestDto map(User user);

    User map(UserRegRequestDto userRegRequestDto);
}
