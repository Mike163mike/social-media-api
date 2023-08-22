package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.AbstractTest;
import com.effectivemobile.socialmediaapi.model.Role;
import com.effectivemobile.socialmediaapi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    void editUserRoles() {
        User user = new User();
        Role roleU = new Role();
        roleU.setName("ROLE_USER");
        user.setRoles(List.of(roleU));
        user.setUsername("User");
        userRepository.save(user);
        user = userService.editUserRoles("User", List.of("ROLE_USER"));
        String newRole = user.getRoles().stream()
            .map(Role::getName)
            .findFirst()
            .orElseThrow();
        Assertions.assertArrayEquals("ROLE_USER".toCharArray(), newRole.toCharArray());
    }
}