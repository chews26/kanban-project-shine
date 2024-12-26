package com.example.prello.list.dto;

import com.example.prello.list.entity.Deck;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class DeckResponseDto {

    private final Long id;
    private final String title;
    private final int order;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static DeckResponseDto toDto(Deck deck) {
        return new DeckResponseDto(
                deck.getId(),
                deck.getTitle(),
                deck.getOrder(),
                deck.getCreatedAt(),
                deck.getUpdatedAt()
        );
    }
}
