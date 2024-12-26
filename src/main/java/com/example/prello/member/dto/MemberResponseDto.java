package com.example.prello.member.dto;

import com.example.prello.member.auth.MemberAuth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final MemberAuth memberAuth;
}
