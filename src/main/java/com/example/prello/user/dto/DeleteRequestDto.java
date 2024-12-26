package com.example.prello.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteRequestDto {

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;

}