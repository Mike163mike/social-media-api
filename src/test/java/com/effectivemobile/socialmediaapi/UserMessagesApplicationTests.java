package com.effectivemobile.socialmediaapi;

import com.effectivemobile.socialmediaapi.controller.AuthController;
import com.effectivemobile.socialmediaapi.controller.MessageController;
import com.effectivemobile.socialmediaapi.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMessagesApplicationTests extends AbstractTest {

    @Autowired
    private MessageController messageController;

    @Autowired
    private UserController userController;

    @Autowired
    private AuthController authController;

    @Test
    void contextLoadsMessage() {
        assertThat(messageController).isNotNull();
    }

    @Test
    void contextLoadsUser() {
        assertThat(userController).isNotNull();
    }

    @Test
    void contextLoadsAuth() {
        assertThat(authController).isNotNull();
    }
}
