package com.example.prello.card;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CardAssigneesRequestDto {

    @NotNull
    private Long userId;
}
