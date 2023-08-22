package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Page<Post> findAllByPublisherId(UUID id, Pageable pageable);

    List<Post> findAllByPublisherId(UUID ud);
}
