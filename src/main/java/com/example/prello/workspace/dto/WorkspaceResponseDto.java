package com.example.prello.workspace.dto;

import com.example.prello.workspace.entity.Workspace;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class WorkspaceResponseDto {

    private final Long id;

    private final String title;

    private final String description;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static WorkspaceResponseDto toDto(Workspace workspace) {
        return WorkspaceResponseDto.builder()
                .id(workspace.getId())
                .title(workspace.getTitle())
                .description(workspace.getDescription())
                .createdAt(workspace.getCreatedAt())
                .updatedAt(workspace.getUpdatedAt())
                .build();
    }
}
