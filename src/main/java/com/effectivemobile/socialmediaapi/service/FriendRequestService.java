package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.exception.AppException;
import com.effectivemobile.socialmediaapi.model.FriendRequest;
import com.effectivemobile.socialmediaapi.model.User;
import com.effectivemobile.socialmediaapi.repository.FriendRequestRepository;
import com.effectivemobile.socialmediaapi.repository.UserRepository;
import com.effectivemobile.socialmediaapi.security.SecurityContextService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FriendRequestService {

    private final FriendRequestRepository repository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    public FriendRequest addFriend(UUID friendId) {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        sender.getFollow().add(friendId);
        sender = userRepository.save(sender);

        User receiver = userRepository.findById(friendId)
                .orElseThrow(() -> new AppException("User not found."));
        receiver.getFollowers().add(sender.getId());

        receiver = userRepository.save(receiver);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);

        return repository.save(friendRequest);
    }

    public List<FriendRequest> getOutRequests() {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        return sender.getOutRequests();
    }

    public List<FriendRequest> getInRequests() {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        return sender.getInRequests();
    }

    public void acceptRequest(FriendRequest friendRequest) {
        repository.delete(friendRequest);
    }

    public void declineRequest(FriendRequest friendRequest) {
        User receiver = userRepository.findById(friendRequest.getReceiver().getId())
                        .orElseThrow(() -> new AppException("User not found."));
        receiver.getFollowers().remove(friendRequest.getSender().getId());
        userRepository.save(receiver);
        repository.delete(friendRequest);
    }

    public void removeFriend(UUID id) {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        sender.getFollowers().remove(id);
        userRepository.save(sender);
    }
}

