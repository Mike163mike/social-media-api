package com.effectivemobile.socialmediaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialMediaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaApiApplication.class, args);
    }

}
