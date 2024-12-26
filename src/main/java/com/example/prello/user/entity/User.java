package com.example.prello.user.entity;

import com.example.prello.common.BaseEntity;
import com.example.prello.user.dto.SignUpRequestDto;
import com.example.prello.user.enums.Auth;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "`user`")
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Auth auth;

    private LocalDateTime deletedAt;

    public User() {}

    public User(String email, String name, String password, Auth auth) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.auth = auth;
        this.deletedAt = null;
    }

    public void deleteSoftly() {
        this.deletedAt = LocalDateTime.now();
    }
}
