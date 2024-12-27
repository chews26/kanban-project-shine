package com.example.prello.member.dto;

import com.example.prello.member.auth.MemberAuth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "권한은 필수 입력값 입니다.")
    @Size(max = 8, message = "입력 범위를 초과하였습니다.")
    private final MemberAuth auth;

    @NotBlank(message = "이메일은 필수 입니다.")
    @Size(max = 50, message = "이메일의 최대길이는 50자 입니다.")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$", message = "올바르지 않은 이메일 형식입니다.")
    private final String email;

}