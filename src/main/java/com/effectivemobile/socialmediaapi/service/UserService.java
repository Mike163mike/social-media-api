package com.effectivemobile.socialmediaapi.service;

import com.effectivemobile.socialmediaapi.exception.AppException;
import com.effectivemobile.socialmediaapi.model.Role;
import com.effectivemobile.socialmediaapi.model.User;
import com.effectivemobile.socialmediaapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final List<String> STRING_ROLES = List.of("ROLE_USER");


    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(getRoles(List.of("ROLE_USER")));
        userRepository.save(user);
    }

    public User editUserRoles(String username, List<String> roles) {
        User user = userRepository.findUserByUsername(username);
        if (checkList(roles)) {
            user.setRoles(getRoles(roles));
        } else {
            throw new AppException("Incorrect name of role.Source: " +
                    "UserService.");
        }
        user.setEditTime(Instant.now());
        userRepository.save(user);    //These two steps do-
        return user;                  // only for normal running test cases.
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional
    public Integer deleteUser(String username) {
        return userRepository.deleteUserByUsername(username);
    }

    private boolean checkList(List<String> roles) {
        List<String> newRoles = roles.stream()
                .map(String::toUpperCase)
                .toList();
        return STRING_ROLES.containsAll(newRoles);
    }

    List<Role> getRoles(List<String> strings) {
        List<Role> newRoles = new ArrayList<>();
        for (String tempStr : strings) {
            Role newRole = new Role();
            newRole.setName(tempStr.toUpperCase());
            newRoles.add(newRole);
        }
        return newRoles;
    }
}

