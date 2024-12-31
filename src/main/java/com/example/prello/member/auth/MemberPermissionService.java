package com.example.prello.member.auth;

import com.example.prello.exception.CustomException;
import com.example.prello.exception.MemberErrorCode;
import com.example.prello.security.session.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPermissionService {

    private final SessionUtils sessionUtils;

    // READ_ONLY 권한 이상 검증
    public void validateReadOnlyAccess(Long workspaceId) {
        MemberAuth memberAuth = sessionUtils.getWorkspacePermission(workspaceId).getAuth();
        if (!memberAuth.hasPermission(MemberAuth.READ_ONLY)) {
            throw new CustomException(MemberErrorCode.ACCESS_FORBIDDEN);
        }
    }

    // BOARD 권한 이상 검증
    public void validateBoardAccess(Long workspaceId) {
        MemberAuth memberAuth = sessionUtils.getWorkspacePermission(workspaceId).getAuth();
        if (!memberAuth.hasPermission(MemberAuth.BOARD)) {
            throw new CustomException(MemberErrorCode.ACCESS_FORBIDDEN);
        }
    }

    // WORKSPACE 권한 검증
    public void validateWorkspaceAccess(Long workspaceId) {
        MemberAuth memberAuth = sessionUtils.getWorkspacePermission(workspaceId).getAuth();
        if (!memberAuth.hasPermission(MemberAuth.WORKSPACE)) {
            throw new CustomException(MemberErrorCode.ACCESS_FORBIDDEN);
        }
    }
}