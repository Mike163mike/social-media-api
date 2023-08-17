package com.effectivemobile.socialmediaapi.dto;

import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private List<Role> roles;
    private List<Message> messages;
    private Instant createTime;
    private Instant editTime;
}
