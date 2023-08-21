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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        UUID publisherId = postService.getMyId();  //crutch
        PostDto newPostDto = postMapper.map(postService
                .savePost(postMapper.map(postDto)));
        newPostDto.setPublisherId(publisherId); //crutch
        return ResponseEntity.ok(newPostDto);
    }

    @PutMapping("/{post_id}")
    @Operation(summary = "Edit my post")
    public ResponseEntity<PostDto> editPostById(@PathVariable UUID post_id,
                                                @RequestBody PostDto postDto) {
        UUID publisherId = postService.getMyId();  //crutch
        PostDto newPostDto = postMapper.map(
                postService.editPostById(post_id, postMapper.map(postDto)));
        newPostDto.setPublisherId(publisherId); //crutch
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
    public ResponseEntity<Page<PostDto>> getAllPostsByOneUser(@PathVariable UUID id,
                                                              @PageableDefault Pageable pageable) {
        Page<PostDto> postDtos = postService.findAllPostsByPublisherId(id, pageable)
                .map(postMapper::map);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping
    @Operation(summary = "Get pages of all my posts")
    public ResponseEntity<Page<PostDto>> getAllMyPosts(@PageableDefault Pageable pageable) {
        Page<PostDto> postDtos = postService.findAllPostsByPublisherId(postService.getMyId(),
                        pageable)
                .map(postMapper::map);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/page")
    @Operation(summary = "Get pages of all users posts i am follow .")
    public ResponseEntity<Page<Post>> getCustomers(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {

        Page<Post> customerPage = postService.getAllPosts(page, size);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Page-Number", String.valueOf(customerPage.getNumber()));
        headers.add("X-Page-Size", String.valueOf(customerPage.getSize()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(customerPage);
    }
}
