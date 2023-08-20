package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.Message;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> getMessagesBySenderId(UUID id);

    List<Message> getMessagesByReceiverId(UUID id);

    // void save(List<Message> messages);
}
