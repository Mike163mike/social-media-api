package com.effectivemobile.socialmediaapi.controller;

import com.effectivemobile.socialmediaapi.dto.MessageRequestDto;
import com.effectivemobile.socialmediaapi.dto.MessageResponseDto;
import com.effectivemobile.socialmediaapi.mapper.MessageRequestMapper;
import com.effectivemobile.socialmediaapi.mapper.MessageResponseMapper;
import com.effectivemobile.socialmediaapi.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RequestMapping("/message")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Messages", description = "The Message API. Contains operations with " +
        "messages.")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MessageResponseMapper messageResponseMapper;
    private final MessageRequestMapper messageRequestMapper;

    @Operation(summary = "Create new message for my friend")
    @PostMapping("/{id}")
    public ResponseEntity<MessageResponseDto> createMessage(@PathVariable UUID id,
                                                            @RequestBody MessageRequestDto messageRequestDto) {
        MessageResponseDto newMessageResponseDto = messageResponseMapper.map(messageService
                .saveMessage(messageRequestMapper
                        .map(messageRequestDto)));
        return ResponseEntity.ok(newMessageResponseDto);
    }

//    @Operation(summary = "Get pages of messages", description = "Get all messages published by particular user.")
//    @GetMapping("/page/{username}")
//    public ResponseEntity<Page<Message>> getAllMessages(@PathVariable(name = "username") String username) {
//        Page<Message> messageResponseDtos = messageService.findAllMessagesByUserId(username);
//        return ResponseEntity.ok(messageResponseDtos);
//    }


//    @Operation(summary = "Get all messages", description = "get all messages from all " +
//            "users.")
//    @GetMapping
//    public ResponseEntity<List<MessageResponseDto>> getAllMessages() {
//        List<MessageResponseDto> messageResponseDtos = messageResponseMapper
//                .toList(messageService.getAllMessages());
//        return ResponseEntity.ok(messageResponseDtos);
//    }

    @GetMapping("/{message_id}")
    @Operation(summary = "Get message by id")
    public ResponseEntity<MessageResponseDto> getMessageById(@PathVariable(name = "message_id") UUID message_id) {
        try {
            messageService.findById(message_id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Not found message with id = " + message_id + ".");
        }
        MessageResponseDto messageResponseDto = messageResponseMapper.map(messageService.findById(message_id));
        return ResponseEntity.ok(messageResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit message")
    public ResponseEntity<MessageResponseDto> editMessageById(@PathVariable UUID id,
                                                              @RequestBody String message) {
        try {
            messageService.findById(id);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Not found message with id = " + id + ".");
        }
        MessageResponseDto messageResponseDto = messageResponseMapper.map(
                messageService.editMessageById(id, message));
        return ResponseEntity.ok(messageResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete message by id")
    public ResponseEntity<String> deleteMessageById(@PathVariable UUID id) {
        try {
            messageService.findById(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Not found message with id = " + id + ".", HttpStatus.NOT_FOUND);
        }
        messageService.deleteMessageById(id);
        return ResponseEntity.ok("The message with id = " + id + " was deleted successfully.");
    }
}
