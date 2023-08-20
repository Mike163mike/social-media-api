package com.effectivemobile.socialmediaapi.dto;

import com.effectivemobile.socialmediaapi.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MessageResponseDto {

    private String message;

    private UUID senderId;
}
