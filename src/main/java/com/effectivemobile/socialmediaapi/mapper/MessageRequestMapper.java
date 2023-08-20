package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.MessageRequestDto;
import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.model.User;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper
public interface MessageRequestMapper {

    MessageRequestDto map(Message message);

    Message map(MessageRequestDto messageRequestDto);

}
