package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.exception.AppException;
import com.effectivemobile.socialmediaapi.model.Post;
import com.effectivemobile.socialmediaapi.model.User;
import com.effectivemobile.socialmediaapi.repository.PostRepository;
import com.effectivemobile.socialmediaapi.repository.UserRepository;
import com.effectivemobile.socialmediaapi.security.SecurityContextService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    public Post savePost(Post post) {
        User publisher = userRepository.findUserByUsername(securityContextService.getUserName());
        post.setPublisher(publisher);
        return postRepository.save(post);
    }

    public Post editPostById(UUID id, Post post) {
        User publisher = userRepository.findUserByUsername(securityContextService.getUserName());
        Post postInRep = postRepository.findById(id)
                .orElseThrow(() -> new AppException("Post with id = " + id + " not found. Source: " +
                        "PostService."));
        postInRep.setTitle(post.getTitle());
        postInRep.setMessage(post.getMessage());
        postInRep.setImage(post.getImage());
        postInRep.setPublisher(publisher);
        return postRepository.save(postInRep);
    }

    public void deletePostById(UUID id) {
        postRepository.deleteById(id);
    }

    public Page<Post> findAllPostsByPublisherId(UUID id, Pageable pageable) {
        return postRepository.findAllByPublisherId(id, pageable);
    }

    public UUID getMyId() {
        User user = userRepository.findUserByUsername(securityContextService.getUserName());
        return user.getId();
    }

    public Page<Post> getAllPosts(int page, int size) {
        User me = userRepository.findById(getMyId())
                .orElseThrow(() -> new AppException("User with id = " + getMyId() + " not found.Source: " +
                        "PostService."));
        List<UUID> follows = me.getFollow();
        List<List<Post>> pageArrayList = new ArrayList<>();
        for (UUID uuid : follows) {
            List<Post> oneUserPage = postRepository.findAllByPublisherId(uuid);
            pageArrayList.add(oneUserPage);
        }
        List<Post> postList = pageArrayList
                .stream()
                .flatMap(Collection::stream)
                .toList();

        Pageable pageRequest = createPageRequestUsing(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), postList.size());
        List<Post> pagePost = postList.subList(start, end);
        return new PageImpl<>(pagePost, pageRequest, postList.size());
    }

    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }
}

