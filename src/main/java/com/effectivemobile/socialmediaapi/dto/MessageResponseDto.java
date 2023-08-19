package com.effectivemobile.socialmediaapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class MessageResponseDto {

    private UUID id;
    private String title;
    private String message;
    private String image;
    private Instant createTime;
    private Instant editTime;
    private Integer userId;
}
