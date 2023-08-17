package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Integer> {
}
