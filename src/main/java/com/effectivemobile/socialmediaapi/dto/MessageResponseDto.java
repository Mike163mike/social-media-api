package com.effectivemobile.socialmediaapi.dto;

import com.effectivemobile.socialmediaapi.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessageResponseDto {

    private Instant editTime;
    private String message;
    private User sender;
    private User receiver;
}
