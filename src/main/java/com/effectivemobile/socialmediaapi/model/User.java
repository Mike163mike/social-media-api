package com.effectivemobile.socialmediaapi.model;

import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @Type(ListArrayType.class)
    @Column(
            name = "my_subscribers",
            columnDefinition = "int[]"
    )
    private List<Integer> mySubscribers;
    @Type(ListArrayType.class)
    @Column(
            name = "i_subscribe",
            columnDefinition = "int[]"
    )
    private List<Integer> iSubscribe;
}
