package com.example.prello.member.dto;

import com.example.prello.member.auth.MemberAuth;
import com.example.prello.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class MemberResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    private final MemberAuth memberAuth;

    public static MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
//                .name(member.getUser().getName()) // 예: Member에서 User의 name 가져오기
//                .email(member.getUser().getEmail()) // 예: Member에서 User의 email 가져오기
                .memberAuth(member.getAuth())
                .build();
    }
}
