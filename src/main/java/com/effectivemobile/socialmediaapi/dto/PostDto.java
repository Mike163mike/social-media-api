package com.effectivemobile.socialmediaapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PostDto {

    private String title;

    private String message;

//    private byte[] image;
    private String image;

    private UUID publisherId;
}
