package com.example.prello.list;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class DeckRequestDto {

    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    @Size(max = 20, message = "20자 이내로 작성해야 합니다.")
    private String title;

    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    private int order;
}
