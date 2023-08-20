package com.effectivemobile.socialmediaapi.mapper;

import com.effectivemobile.socialmediaapi.dto.MessageResponseDto;
import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.model.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface MessageResponseMapper {

    MessageResponseDto map(Message message);

    Message map(MessageResponseDto messageResponseDto);

    default List<MessageResponseDto> toListToDto(List<Message> messages) {
        return messages.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
