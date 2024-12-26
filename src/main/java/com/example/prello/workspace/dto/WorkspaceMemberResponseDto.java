package com.example.prello.workspace.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WorkspaceMemberResponseDto {

    private final String email;

    // todo MemberAuth Enum으로 변경 필요
    private final String auth;
//    private final MemberAuth memberAuth;

}
