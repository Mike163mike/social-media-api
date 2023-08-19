package com.effectivemobile.socialmediaapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserSubscriberDto {

    private UUID id;
    private String username;
}
