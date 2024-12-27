package com.example.prello.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "내용을 입력하세요")
    @Size(max = 255, message = "255자 이내로 작성해야 합니다.")
    private String content;
}
