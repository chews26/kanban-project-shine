package com.example.prello.workspace.dto;

import com.example.prello.member.auth.MemberAuth;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkspacePermissionDto {
    private Long workspaceId;
    private MemberAuth auth;
}