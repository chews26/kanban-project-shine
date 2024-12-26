package com.example.prello.member.service;

import com.example.prello.member.auth.MemberAuth;
import com.example.prello.member.repository.MemberRepository;
import com.example.prello.member.dto.MemberRequestDto;
import com.example.prello.member.dto.MemberResponseDto;
import com.example.prello.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // todo 멤버 권한 변경
    // todo return값 변경 필요
    @Transactional
    public MemberResponseDto updateMemberAuth(Long memberId, MemberRequestDto memberRequestDto) throws IllegalAccessException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버를 찾을 수 없습니다. ID: " + memberId));

        MemberAuth currentAuth = member.getAuth();

        member.updateMemberAuth(memberRequestDto.getMemberAuth());

        return MemberResponseDto.toDto(member);
    }
}
