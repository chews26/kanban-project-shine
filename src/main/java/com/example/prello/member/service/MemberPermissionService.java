package com.example.prello.member.service;

import com.example.prello.member.auth.MemberAuth;
import com.example.prello.member.repository.MemberRepository;
import com.example.prello.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberPermissionService {
    private final MemberRepository memberRepository;
    private final WorkspaceRepository workspaceRepository;


     // 워크스페이스 소유자 여부 확인
    public void validateWorkspaceOwner(Long workspaceId, Long userId) {
        boolean isOwner = workspaceRepository.existsByIdAndOwnerId(workspaceId, userId);
        if (!isOwner) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "워크스페이스 소유자가 아닙니다.");
        }
    }

    // 워크스페이스 멤버 여부 및 권한 확인
    public void validateWorkspaceAccess(Long workspaceId, Long userId, MemberAuth requiredAuth) {
        boolean isMember = memberRepository.existsByUserIdAndWorkspaceId(userId, workspaceId);
        if (!isMember) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "워크스페이스 멤버가 아닙니다.");
        }

        MemberAuth currentAuth = memberRepository.findMemberAuthByUserIdAndWorkspaceId(userId, workspaceId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "권한 정보를 찾을 수 없습니다."));

        if (currentAuth.ordinal() < requiredAuth.ordinal()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 부족합니다.");
        }
    }
}
