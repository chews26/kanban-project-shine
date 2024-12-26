package com.example.prello.card;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardRequestDto {

    @NotEmpty
    private String title;

    private String description;

    @NotNull
    private LocalDateTime endAt;
}
