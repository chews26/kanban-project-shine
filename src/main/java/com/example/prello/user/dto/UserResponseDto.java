package com.example.prello.user.dto;

import com.example.prello.user.entity.User;
import com.example.prello.user.enums.Auth;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private final Long userId;
    private final String email;
    private final String name;
    private final Auth auth;

    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .auth(user.getAuth())
                .build();
    }
}
