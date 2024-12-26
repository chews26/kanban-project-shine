package com.example.prello.user.dto;

import com.example.prello.user.enums.Auth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이메일 아이디는 필수입니다.")
    @Email(message = "이메일 주소 형식을 확인해주세요.")
    private String email;
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
    @NotNull(message = "권한은 필수입니다.")
    private Auth auth;

    public SignUpRequestDto(String email, String name, String password, Auth auth) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.auth = auth;
    }
}
