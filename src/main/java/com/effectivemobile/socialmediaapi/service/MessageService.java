package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.exception.AppException;
import com.effectivemobile.socialmediaapi.model.Message;
import com.effectivemobile.socialmediaapi.model.User;
import com.effectivemobile.socialmediaapi.repository.FriendRequestRepository;
import com.effectivemobile.socialmediaapi.repository.MessageRepository;
import com.effectivemobile.socialmediaapi.repository.UserRepository;
import com.effectivemobile.socialmediaapi.security.SecurityContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    public Message saveMessage(Message message, UUID userId) {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        User receiver = userRepository.findById(userId).orElseThrow(() -> new AppException("User with id = "
                + userId + " not found."));
        if (isFriends(sender, receiver)) {
            message.setReceiver(receiver);
            message.setSender(sender);
            return messageRepository.save(message);
        } else {
            throw new AppException("User with id = " + userId + " is not your friend. Sending the message impossible.");
        }
    }

    public List<Message> getAllUnreadMessages() {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
        List<Message> newMessages = messageRepository.getMessagesByReceiverId(receiver.getId()).stream()
                .filter(message -> !message.isRead())
                .toList();
        for (Message message : newMessages) {
            message.setRead(true);
            messageRepository.save(message);
        }
      //  messageRepository.save(newMessages);
        receiver.getMessagesIn().addAll(newMessages);
        userRepository.save(receiver);
        return newMessages;
    }

    public List<Message> getAllMessagesFromMyFriends() {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
         List<Message> messages = messageRepository.getMessagesByReceiverId(receiver.getId());
        for (Message message : messages) {
            message.setRead(true);
            messageRepository.save(message);
        }
        return messages;
    }

    public List<Message> getAllMessagesFromSendersIds(UUID senderId) {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
        List<Message> messages = new ArrayList<>();
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new AppException("User with id = " + senderId + " not found."));
        if (isFriends(receiver, sender)) {
            messages = messageRepository.getMessagesBySenderId(senderId).stream()
                    .filter(message -> message.getReceiver().getId().equals(receiver.getId()))
                    .toList();
        }
        for (Message message : messages) {
            message.setRead(true);
            messageRepository.save(message);
        }
        return messages;
    }

    public List<Message> getNewMessagesFromMyFriend(UUID userId) {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
        List<Message> messages = new ArrayList<>();
        User sender = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User with id = " + userId + " not found."));
        if (isFriends(receiver, sender)) {
            messages = messageRepository.getMessagesBySenderId(userId).stream()
                    .filter(message -> message.getReceiver().getId().equals(receiver.getId()))
                    .filter(message -> !message.isRead())
                    .toList();
        }
        for (Message message : messages) {     //crutch
            message.setRead(true);
            messageRepository.save(message);
        }
        return messages;
    }

    public void deleteMyMessageById(UUID messageId) {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
        receiver.getMessagesOut().remove(messageId);
        userRepository.save(receiver);
        messageRepository.deleteById(messageId);
    }

    public void deleteOldAndReadMessage(List<Message> messages) {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
        for (Message message : messages) {
            if (message.isRead() && message.getEditTime().isBefore(Instant.now()
                    .minus(10, ChronoUnit.DAYS))) {
                messageRepository.deleteById(message.getId());
                receiver.getMessagesIn().remove(message.getId());
            }
        }
        userRepository.save(receiver);
    }

    public Message editMyMessageById(UUID messageId, String newMessage) {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new AppException("Message with id = " + messageId + " not found."));
        message.setMessage(newMessage);
        message.setSender(sender);
        messageRepository.save(message);
        return message;
    }

    private boolean isFriends(User userOne, User userTwo) {
        List<UUID> userOneFollowers = userOne.getFollowers();
        List<UUID> userOneFollow = userOne.getFollow();
        List<UUID> userTwoFollowers = userOne.getFollowers();
        List<UUID> userTwoFollow = userOne.getFollow();
        boolean isFriend = false;
        for (UUID tempUUID1 : userOneFollowers) {
            for (UUID tempUUID2 : userTwoFollow) {
                if (tempUUID1.equals(tempUUID2)) {
                    isFriend = true;
                    break;
                }
            }
        }
        for (UUID tempUUID1 : userOneFollow) {
            for (UUID tempUUID2 : userTwoFollowers) {
                if (tempUUID1.equals(tempUUID2)) {
                    isFriend = true;
                    break;
                }
            }
        }
        return isFriend;
    }
}
