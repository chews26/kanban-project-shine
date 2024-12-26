package com.example.prello.workspace;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkspaceMemberResponseDto {

    private final String email;

    // todo MemberAuth Enum으로 변경 필요
    private final String auth;
//    private final MemberAuth memberAuth;

}
