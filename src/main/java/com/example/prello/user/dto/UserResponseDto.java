package com.example.prello.user.dto;

import com.example.prello.user.entity.User;
import com.example.prello.user.enums.Auth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String email;
    private String name;
    private Auth auth;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.auth = user.getAuth();
    }
}
