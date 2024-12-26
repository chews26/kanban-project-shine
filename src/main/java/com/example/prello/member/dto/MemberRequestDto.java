package com.example.prello.member.dto;

import com.example.prello.member.auth.MemberAuth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "권한은 필수 입력값 입니다.")
    @Size(max = 8, message = "입력 범위를 초과하였습니다.")
    private final MemberAuth memberAuth;

}