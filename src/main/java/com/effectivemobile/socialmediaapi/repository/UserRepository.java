package com.effectivemobile.socialmediaapi.repository;

import com.effectivemobile.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

   User findUserByUsername(String username);
   Integer deleteUserByUsername(String username); //Why the method can return Integer type only?*********
}
