package com.example.prello.workspace.dto;

import com.example.prello.workspace.entity.Workspace;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class WorkspaceResponseDto {

    private final Long id;

    private final String title;

    private final String description;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static WorkspaceResponseDto toDto(Workspace workspace) {
        return new WorkspaceResponseDto(
                workspace.getId(),
                workspace.getTitle(),
                workspace.getDescription(),
                workspace.getCreatedAt(),
                workspace.getUpdatedAt()
        );
    }
}
