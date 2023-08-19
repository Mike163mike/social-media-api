package com.effectivemobile.socialmediaapi.controller;

import com.effectivemobile.socialmediaapi.dto.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RequestMapping("/post")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Post", description = "The Post API. Contains operations with posts in system.")
@AllArgsConstructor
public class PostController {

//    @PostMapping
//    @Operation(summary = "Create new post")
//    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
//        PostDto newPostDto = messageResponseMapper.map(messageService
//                .saveMessage(messageRequestMapper
//                        .map(messageRequestDto)));
//        return ResponseEntity.ok(newMessageResponseDto);
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Edit post")
//    public ResponseEntity<PostDto> editPostById(@PathVariable UUID id,
//                                                @RequestBody PostDto postDto) {
//        try {
//            messageService.findById(id);
//        } catch (NoSuchElementException e) {
//            throw new NoSuchElementException("Not found message with id = " + id + ".");
//        }
//        PostDto newPostDto = messageResponseMapper.map(
//                messageService.editMessageById(id, message));
//        return ResponseEntity.ok(messageResponseDto);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Delete post by id")
//    public ResponseEntity<?> deletePostById(@PathVariable UUID id) {
//        try {
//            messageService.findById(id);
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.notFound().build();
//        }
//        messageService.deleteMessageById(id);
//        return ResponseEntity.ok("The message with id = " + id + " was deleted successfully.");
//    }
//    @GetMapping("/page/{username}")
//    @Operation(summary = "Get pages of posts")
//    public ResponseEntity<Page<PostDto>> getAllPostsByOneUser(@PathVariable(name = "username") String username) {
//        Page<PostDto> postDtos = messageService.findAllMessagesByUserId(username);
//        return ResponseEntity.ok(messageResponseDtos);
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "Get pages of all my posts")
//    public ResponseEntity<Page<PostDto>> getAllPostsOfMy() {
//        Page<PostDto> postDtos = messageService.findAllMessagesByUserId(username);
//        return ResponseEntity.ok(messageResponseDtos);
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "Get pages of posts of all users i follow.")
//    public ResponseEntity<Page<PostDto>> getAllPostsOfMy() {
//        Page<PostDto> postDtos = messageService.findAllMessagesByUserId(username);
//        return ResponseEntity.ok(messageResponseDtos);
//    }
}
