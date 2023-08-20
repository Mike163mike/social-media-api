package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> getMessagesBySenderId(UUID id);

    List<Message> getMessagesByReceiverId(UUID id);
}
