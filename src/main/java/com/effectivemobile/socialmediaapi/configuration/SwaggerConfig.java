package com.effectivemobile.socialmediaapi.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Social Media Api")
                        .version("1.0.0")
                        .description("Social media platform. App for safe create, edit and delete posts, make friends, " +
                                "send and receive messages from them. For free communication.")
                        .contact(
                                new Contact()
                                        .email("mick_mick_mick@icloud.com")
                                        .name("Mike")));
    }

}