package com.effectivemobile.socialmediaapi.model;

import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Role> roles;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Message> messages;
    @CreatedDate
    private Instant createTime;
    @LastModifiedDate
    private Instant editTime;
    @Type(StringArrayType.class)
    @Column(
            name = "my_subscribers",
            columnDefinition = "String[]"
    )
    private List<UUID> mySubscribers;
    @Type(StringArrayType.class)
    @Column(
            name = "i_subscribe",
            columnDefinition = "uuid[]"
    )
    private List<UUID> iSubscribe;
}
