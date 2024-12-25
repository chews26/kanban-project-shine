package com.example.prello.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final Auth auth;
}
