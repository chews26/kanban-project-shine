package com.example.prello.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardRequestDto {

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    @Size(max = 20, message = "제목은 20자 이내로 입력해야 합니다.")
    private final String title;

    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "컬러코드 형식이 맞지 않습니다.")
    @Size(min = 7, max = 7, message = "색상코드는 7글자입니다.")
    private final String bgColor;

    private final String bgImage;
}
