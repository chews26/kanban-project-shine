package com.example.prello.user.dto;

import com.example.prello.user.entity.User;
import com.example.prello.user.enums.Auth;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    @NotBlank(message = "이메일 아이디는 필수입니다.")
    @Size(max = 50, message = "이메일의 최대길이는 50자입니다.")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$", message = "올바르지 않은 이메일 형식입니다.")
    private final String email;

    @NotBlank(message = "이름은 필수입니다.")
    private final String name;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;

    private final Auth auth = Auth.USER;

}
