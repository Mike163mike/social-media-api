package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.UserSubscriberDto;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface UserSubscriberMapper {

    UserSubscriberDto map(User user);
}
