package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

   User findUserByUsername(String username);
   Integer deleteUserByUsername(String username); //Why the method can return Integer type only?*********
}
