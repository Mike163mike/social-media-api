package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MessageRepository extends JpaRepository<Message, UUID> {

     Page<Message> findAllByUserUsername(String username, Pageable pageable);
}
