package com.example.prello.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LoginRequestDto {

    private String email;
    private String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
