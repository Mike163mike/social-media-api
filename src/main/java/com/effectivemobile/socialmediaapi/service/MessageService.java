package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message findById(Integer id) {
        return messageRepository.findById(id)
                .orElseThrow();
    }

    public void deleteMessageById(Integer id) {
        messageRepository.deleteById(id);
    }

    public Message editMessageById(Integer id, String newMessage) {
        Message message = messageRepository.findById(id).orElseThrow();
        message.setMessage(newMessage);
        messageRepository.save(message);
        return message;
    }
}
