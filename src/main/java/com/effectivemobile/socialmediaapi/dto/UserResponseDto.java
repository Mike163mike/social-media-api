package com.effectivemobile.socialmediaapi.dto;

import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {

    private UUID id;
    private String username;
    private String email;
    private List<Role> roles;
    private List<Message> messages;
    private Instant createTime;
    private Instant editTime;
    private List<UUID> followers;
    private List<UUID> follow;
}
