package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {

    List<FriendRequest> findFriendRequestByReceiverId(UUID id);

    List<FriendRequest> findFriendRequestBySenderId(UUID id);
}
