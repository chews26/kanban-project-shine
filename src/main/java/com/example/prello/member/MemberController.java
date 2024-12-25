package com.example.prello.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/members")
public class MemberController {

    private final MemberService memberService;

    //todo 멤버 권한 변경
    @PatchMapping("/{Id}")
    public ResponseEntity<MemberResponseDto> updateMemberAuth(@PathVariable Long Id, @RequestBody MemberRequestDto memberRequestDto
    ) throws IllegalAccessException {
        MemberResponseDto updatedMemberAuth = memberService.updateMemberAuth(Id, memberRequestDto);
        return ResponseEntity.ok().body(updatedMemberAuth);
    }
}
