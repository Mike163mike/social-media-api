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

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    public FriendRequest addFriend(UUID friendId) {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        sender.getFollow().add(friendId);
        sender = userRepository.save(sender);
        User receiver = userRepository.findById(friendId)
                .orElseThrow(() -> new AppException("User not found here. Source: " +
                        "FriendRequestController."));
        receiver.getFollowers().add(sender.getId());
        receiver = userRepository.save(receiver);
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        return friendRequestRepository.save(friendRequest);
    }

    public List<FriendRequest> getOutRequests() {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        return friendRequestRepository.findAllBySenderId(sender.getId());
    }

    public List<FriendRequest> getInRequests() {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        return friendRequestRepository.findAllByReceiverId(sender.getId());
    }

//    public FriendRequest getInRequests(UUID id) {
//        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
//        return friendRequestRepository.findFriendRequestByReceiverId(sender.getId());
////        return friendRequestRepository.findFriendRequestById(id);
//    }

    public void acceptRequest(FriendRequest friendRequest) {
        friendRequestRepository.delete(friendRequest);
    }

    public void declineRequest(FriendRequest friendRequest) {
        User receiver = userRepository.findUserByUsername(securityContextService.getUserName());
        User sender = userRepository.findById(friendRequest.getReceiver().getId())
                .orElseThrow(() -> new AppException("User not found. Source: " +
                        "FriendRequestController."));
        sender.getFollowers().remove(sender.getId());
        receiver.getFollow().remove(receiver.getId());
        userRepository.save(sender);
        userRepository.save(receiver);
        friendRequestRepository.delete(friendRequest);
    }

    public void removeFriend(UUID id) {
        User sender = userRepository.findUserByUsername(securityContextService.getUserName());
        sender.getFollowers().remove(id);
        userRepository.save(sender);
    }
}

