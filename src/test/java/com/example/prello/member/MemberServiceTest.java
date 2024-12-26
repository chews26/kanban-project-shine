package com.example.prello.member;

import com.example.prello.member.auth.MemberAuth;
import com.example.prello.member.dto.MemberRequestDto;
import com.example.prello.member.dto.MemberResponseDto;
import com.example.prello.member.entity.Member;
import com.example.prello.member.repository.MemberRepository;
import com.example.prello.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    // todo test 수정 필요
    @Test
    @DisplayName("멤버 권한 변경 성공")
    void updateMemberAuth() throws IllegalAccessException {
        // Given
        Long memberId = 1L;
        MemberAuth oldAuth = MemberAuth.READ_ONLY;
        MemberAuth newAuth = MemberAuth.BOARD;

        Member member = new Member();
        ReflectionTestUtils.setField(member, "id", memberId);
        ReflectionTestUtils.setField(member, "auth", oldAuth);

        MemberRequestDto requestDto = new MemberRequestDto(newAuth);

        when(memberRepository.findById(eq(memberId))).thenReturn(Optional.of(member));

        // When
        MemberResponseDto responseDto = memberService.updateMemberAuth(memberId, requestDto);

        // Then
        assertNotNull(responseDto);
        assertEquals(memberId, responseDto.getId());
        assertEquals(newAuth, responseDto.getMemberAuth());
    }
}