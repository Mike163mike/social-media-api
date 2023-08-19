package com.effectivemobile.socialmediaapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FriendRequestDto {

    private UUID requestId;
    private UUID friendId;
    private String friendName;
}
