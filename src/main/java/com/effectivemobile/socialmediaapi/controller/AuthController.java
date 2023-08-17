package com.effectivemobile.socialmediaapi.controller;

import com.effectivemobile.socialmediaapi.dto.UserRegRequestDto;
import com.effectivemobile.socialmediaapi.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/security")
@RestController
@Tag(name = "Registration of users", description = "The registration API. The endpoint for registration " +
        "new users with receiving jwt.")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Registration new user and receiving jwt.")
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody UserRegRequestDto userRegRequestDto) {
        authService.createUser(userRegRequestDto);
        return authService.createAuthToken(userRegRequestDto);
    }


    @Operation(summary = "Receiving jwt by registered users. FOR TESTING ONLY!!!!")
    @PostMapping("/getJwt")
    public ResponseEntity<?> getJwt(@RequestParam String username, @RequestParam String password) {
        return authService.createAuthToken(username, password);
    }
}
