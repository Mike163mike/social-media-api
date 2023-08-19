package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.FriendRequestDto;
import com.effectivemobile.socialmediaapi.model.FriendRequest;
import org.mapstruct.Mapper;

@Mapper
public interface FriendRequestMapper {

    FriendRequestDto map(FriendRequest friendRequest);

    FriendRequest map(FriendRequestDto friendRequestDto);
}
