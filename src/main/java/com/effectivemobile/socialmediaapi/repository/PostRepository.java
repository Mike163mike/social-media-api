package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    //     Page<Post> findAllByUserUsername(String username, Pageable pageable);
}
