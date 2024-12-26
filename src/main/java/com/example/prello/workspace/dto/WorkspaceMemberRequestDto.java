package com.example.prello.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WorkspaceMemberRequestDto {

    @NotBlank(message = "이메일은 필수 입니다.")
    @Size(max = 50, message = "이메일의 최대길이는 50자 입니다.")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$", message = "올바르지 않은 이메일 형식입니다.")
    private final String email;

    // todo MemberAuth Enum으로 변경 필요
    @Size(message = "설명은 255자 이내로 입력해야합니다.")
    private final String auth;
//    private final MemberAuth auth;

}