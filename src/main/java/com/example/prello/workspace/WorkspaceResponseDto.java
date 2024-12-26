package com.example.prello.workspace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WorkspaceResponseDto {

    private final String title;

    private final String description;

    private final String createdAt;

    private final String modifiedAt;
}
