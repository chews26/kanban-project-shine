package com.example.prello.user.dto;

import com.example.prello.user.entity.User;
import com.example.prello.user.enums.Auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "이메일 아이디는 필수입니다.")
    @Email(message = "이메일 주소 형식을 확인해주세요.")
    private final String email;

    @NotBlank(message = "이름은 필수입니다.")
    private final String name;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;

    @NotNull(message = "권한은 필수입니다.")
    private final Auth auth;

}
