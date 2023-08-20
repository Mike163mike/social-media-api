package com.effectivemobile.socialmediaapi.controller;

import com.effectivemobile.socialmediaapi.dto.PostDto;
import com.effectivemobile.socialmediaapi.mapper.PostMapper;
import com.effectivemobile.socialmediaapi.model.Post;
import com.effectivemobile.socialmediaapi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/post")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Post", description = "The Post API. Contains operations with posts in system.")
@AllArgsConstructor
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;

    @PostMapping
    @Operation(summary = "Create new post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto newPostDto = postMapper.map(postService
                .savePost(postMapper.map(postDto)));
        return ResponseEntity.ok(newPostDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit my post")
    public ResponseEntity<PostDto> editPostById(@PathVariable UUID id,
                                                @RequestBody PostDto postDto) {
        PostDto newPostDto = postMapper.map(
                postService.editPostById(id, postMapper.map(postDto)));
        return ResponseEntity.ok(newPostDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete post by id")
    public ResponseEntity<?> deletePostById(@PathVariable UUID id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("The post with id = " + id + " was deleted successfully.");
    }

    @GetMapping("/page/{id}")
    @Operation(summary = "Get pages of one user posts by him id")
    public ResponseEntity<Page<PostDto>> getAllPostsByOneUser(@PathVariable UUID id) {
        Page<PostDto> postDtos = postService.findAllPostsByPublisherId(id)
                .stream()
                .map(postMapper::map)
                .map()
                .toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping
    @Operation(summary = "Get pages of all my posts")
    public ResponseEntity<Page<PostDto>> getAllPostsOfMyFollows() {
        Page<PostDto> postDtos = postService.findAllPostsByPublisherId(postService.getMyId())
                .stream()
                .map(postMapper::map)
                .map()
                .toList();;
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/page")
    @Operation(summary = "Get pages of all users i follow posts.")
    public ResponseEntity<Page<PostDto>> getAllPostsOfMyFollows() {
        Page<PostDto> postDtos = postService.findAllPostsOfMyFollows();
        return ResponseEntity.ok(postDtos);
    }
}
