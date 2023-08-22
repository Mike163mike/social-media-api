package com.effectivemobile.socialmediaapi.controller;

import com.effectivemobile.socialmediaapi.dto.MessageRequestDto;
import com.effectivemobile.socialmediaapi.dto.MessageResponseDto;
import com.effectivemobile.socialmediaapi.mapper.MessageRequestMapper;
import com.effectivemobile.socialmediaapi.mapper.MessageResponseMapper;
import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/message")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Messages", description = "The Message API. Contains operations with friends messages.")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MessageResponseMapper messageResponseMapper;
    private final MessageRequestMapper messageRequestMapper;

    @PostMapping("/{id}")
    @Operation(summary = "Create new message for my friend by him id.")
    public ResponseEntity<MessageResponseDto> createMessage(@PathVariable UUID id,
                                                            @RequestBody MessageRequestDto messageRequestDto) {
        MessageResponseDto newMessageResponseDto = messageResponseMapper.map(messageService
                .saveMessage(messageRequestMapper
                        .map(messageRequestDto), id));
//        newMessageResponseDto.setSenderId(id);   //crutch
        return ResponseEntity.ok(newMessageResponseDto);
    }

    @GetMapping("/new")
    @Operation(summary = "Get all new (unread) messages from my friends.")
    public ResponseEntity<List<MessageResponseDto>> getAllNewMessages() {
        List<MessageResponseDto> newMessageResponseDtos = messageService.getAllUnreadMessages().stream()
                .map(messageResponseMapper::map)
                .toList();
        return ResponseEntity.ok(newMessageResponseDtos);
    }

    @PostMapping
    @Operation(summary = "Get all messages from my friends (and automatically delete read and old messages).")
    public ResponseEntity<List<MessageResponseDto>> getAllMessagesFromMyFriends() {
        List<MessageResponseDto> messages = messageService.getAllMessagesFromMyFriends()
                .stream()
                .map(messageResponseMapper::map)
                .toList();
//        List<MessageResponseDto> allMessageResponseDtos = new ArrayList<>();
//        for (Message message : messages) {                                        //crutch
//            UUID senderId = message.getSender().getId();
//            MessageResponseDto messageResponseDto = messageResponseMapper.map(message);
//           messageResponseDto.setSenderId(senderId);
//            allMessageResponseDtos.add(messageResponseDto);
//        }
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/new/{user_id}")
    @Operation(summary = "Get new (unread) messages from my friend by him id.")
    public ResponseEntity<List<MessageResponseDto>> getNewMessagesFromMyFriendById(@PathVariable UUID user_id) {
        List<MessageResponseDto> newMessageResponseDtos = messageService.getNewMessagesFromMyFriend(user_id).stream()
                .map(messageResponseMapper::map)
                .toList();
//        for (MessageResponseDto messageDto : newMessageResponseDtos) {  //crutch
//            messageDto.setSenderId(user_id);
//        }
        return ResponseEntity.ok(newMessageResponseDtos);
    }

    @GetMapping("/all/{user_id}")
    @Operation(summary = "Get all messages from my friend by him id.")
    public ResponseEntity<List<MessageResponseDto>> getAllMessagesFromMyFriendById(@PathVariable UUID user_id) {
        List<MessageResponseDto> messageResponseDtos = messageResponseMapper.toListToDto(messageService
                .getAllMessagesFromSendersIds(user_id));
//        for (MessageResponseDto messageDto : messageResponseDtos) {  //crutch
//            messageDto.setSenderId(user_id);
//        }
        return ResponseEntity.ok(messageResponseDtos);
    }

    @PutMapping("/{message_id}")
    @Operation(summary = "Edit my message by id.")
    public ResponseEntity<MessageResponseDto> editMyMessageById(@PathVariable UUID message_id,
                                                                @RequestBody String message) {
        Message editedMessage = messageService.editMyMessageById(message_id, message); //crutch
        UUID senderId = editedMessage.getSender().getId();
        MessageResponseDto messageResponseDto = messageResponseMapper.map(editedMessage);
//        messageResponseDto.setSenderId(senderId);    //crutch
        return ResponseEntity.ok(messageResponseDto);
    }

    @DeleteMapping("/{message_id}")
    @Operation(summary = "Delete my message by id")
    public ResponseEntity<?> deleteMyMessageById(@PathVariable UUID message_id) {
        messageService.deleteMyMessageById(message_id);
        return ResponseEntity.ok("The message with id = " + message_id + " was deleted successfully.");
    }
}
