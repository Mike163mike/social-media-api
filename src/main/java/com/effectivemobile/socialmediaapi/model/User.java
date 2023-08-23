package com.effectivemobile.socialmediaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonIgnore
    @CreatedDate
    private Instant createTime;
    @JsonIgnore
    @LastModifiedDate
    private Instant editTime;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Role> roles;
    @JsonIgnore
    @Column(unique = true)
    private String username;
    @JsonIgnore
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "publisher_id")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "receiver_id")
    @JsonIgnore
    private List<Message> messagesIn;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    private List<Message> messagesOut;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "receiver_id")
    @JsonIgnore
    private List<FriendRequest> inRequests;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id")
    @JsonIgnore
    private List<FriendRequest> outRequests;
    @Type(ListArrayType.class)
    @Column(
            name = "followers",
            columnDefinition = " uuid[]"
    )
    @JsonIgnore
    private List<UUID> followers = new ArrayList<>();
    @Type(ListArrayType.class)
    @Column(
            name = "follow",
            columnDefinition = "uuid[]"
    )
    @JsonIgnore
    private List<UUID> follow = new ArrayList<>();
}
