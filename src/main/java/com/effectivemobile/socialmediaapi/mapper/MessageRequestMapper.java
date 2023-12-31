package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.MessageRequestDto;
import com.effectivemobile.socialmediaapi.model.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageRequestMapper {

    MessageRequestDto map(Message message);

    Message map(MessageRequestDto messageRequestDto);

}
