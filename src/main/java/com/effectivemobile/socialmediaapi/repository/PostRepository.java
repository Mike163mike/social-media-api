package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Post;
import com.effectivemobile.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    //     Page<Post> findAllByUserUsername(String username, Pageable pageable);

    List<Post> findPostsByUser(User publisher);
}
