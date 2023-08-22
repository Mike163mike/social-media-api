package com.effectivemobile.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedDate
    private Instant createTime;

    @LastModifiedDate
    private Instant editTime;

    private String title;

    private String message;

//    @Lob
    //@Type(type = "org.hibernate.type.ImageType")
//    @JsonIgnore
    private byte[] image;
//    private String image;

    @ManyToOne
    private User user;
}
