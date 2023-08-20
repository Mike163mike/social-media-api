package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.dto.PostDto;
import com.effectivemobile.socialmediaapi.exception.AppException;
import com.effectivemobile.socialmediaapi.model.Post;
import com.effectivemobile.socialmediaapi.model.User;
import com.effectivemobile.socialmediaapi.repository.PostRepository;
import com.effectivemobile.socialmediaapi.repository.UserRepository;
import com.effectivemobile.socialmediaapi.security.SecurityContextService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    //  private final Sort DESC_SORT = Sort.by(Sort.Direction.DESC, "editTime");
    private final Pageable TEN_UNIT = new Pageable() {
        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return 10;
        }

        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return Sort.by(Sort.Direction.DESC, "editTime");
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public Pageable withPage(int pageNumber) {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    };

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post editPostById(UUID id, Post post) {
        Post postInRep = postRepository.findById(id)
                .orElseThrow(() -> new AppException("Post with id = " + id + " not found."));
        postInRep.setTitle(post.getTitle());
        postInRep.setMessage(post.getMessage());
        postInRep.setImage(post.getImage());
        return post;
    }

    public void deletePostById(UUID id) {
        postRepository.deleteById(id);
    }

    public Page<Post> findAllPostsByPublisherId(UUID id) {
        User publisher = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User with id = " + id + " not found."));
        postRepository.findPostsByUser(publisher);
        return null;
    }

    public UUID getMyId() {
        User user = userRepository.findUserByUsername(securityContextService.getUserName());
        return user.getId();
    }

    public List<UUID> getFollowsId() {
        User user = userRepository.findUserByUsername(securityContextService.getUserName());
        return user.getFollow();
    }

    public Page<Post> findAllPostsOfMyFollows() {
        List<UUID> follows = getFollowsId();
        List<User> publishers = new ArrayList<>();
        List<Post> posts = new ArrayList<>();
        for (UUID uuid:follows) {
           publishers.add(userRepository.findById(uuid)
                   .orElseThrow(() -> new AppException("User with id = " + uuid + " not found.")));
        }
        for (User user:publishers) {
            posts.addAll(postRepository.findPostsByUser(user));
        }
        return ;
    }


    //    public Page<Post> findAllPostsByUserId(String username) {
//        return postRepository.findAllByUserUsername(username, TEN_UNIT);
//    }

//    public List<Post> getAllMessages() {
//        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "editTime"));
//    }
}
