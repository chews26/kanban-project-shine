package com.example.prello.workspace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WorkspaceRequestDto {

    @NotBlank(message = "제목을 비워둘 수 없습니다.")
    @Size(max = 20, message = "제목은 20자 이내로 입력해야 합니다")
    private String title;

    @Size(message = "설명은 255자 이내로 입력해야합니다.")
    private String description;
}
