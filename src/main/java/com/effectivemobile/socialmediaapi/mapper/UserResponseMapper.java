package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.UserResponseDto;
import com.effectivemobile.socialmediaapi.model.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserResponseMapper {

    UserResponseDto map(User user);

    User map(UserResponseDto userResponseDto);

    default List<UserResponseDto> toList(List<User> users) {
        return users.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
