package com.example.prello.member.controller;

import com.example.prello.member.dto.MemberRequestDto;
import com.example.prello.member.dto.MemberResponseDto;
import com.example.prello.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/members")
public class MemberController {

    private final MemberService memberService;

    //todo 멤버 권한 변경
    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMemberAuth(
            @PathVariable Long workspaceId,
            @PathVariable Long id,
            @RequestBody MemberRequestDto memberRequestDto
    ) throws IllegalAccessException {
        MemberResponseDto updatedMemberAuth = memberService.updateMemberAuth(workspaceId, id, memberRequestDto);
        return new ResponseEntity<>(updatedMemberAuth, HttpStatus.OK);
    }
}
