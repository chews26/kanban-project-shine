package com.example.prello.list;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BoardListRequestDto {

    @NotBlank
    @Size(max = 20, message = "20자 이내로 작성해야 합니다.")
    private String title;
}
