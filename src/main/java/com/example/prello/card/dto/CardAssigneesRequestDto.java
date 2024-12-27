package com.example.prello.card.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CardAssigneesRequestDto {

    @NotNull
    private Long userId;
}
