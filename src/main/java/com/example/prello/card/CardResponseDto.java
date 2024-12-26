package com.example.prello.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CardResponseDto {

    private final Long id;

    private final String title;

    private final String description;

    private final LocalDateTime endAt;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.endAt = card.getEndAt();
        this.createdAt = card.getCreatedAt();
        this.updatedAt = card.getUpdatedAt();
    }
}
