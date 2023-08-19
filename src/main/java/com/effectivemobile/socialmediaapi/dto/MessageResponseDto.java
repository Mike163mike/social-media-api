package com.effectivemobile.socialmediaapi.dto;

import com.effectivemobile.socialmediaapi.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDto {

    private String message;

    private User sender;
}
