package com.example.prello.user.dto;

import com.example.prello.user.entity.User;
import com.example.prello.user.enums.Auth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationDto {

    private final Long id;
    private final Auth auth;

    public static AuthenticationDto toDto(User user) {
        return new AuthenticationDto(user.getId(), user.getAuth());
    }
}