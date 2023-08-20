package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.FriendRequest;
import com.effectivemobile.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {

}
