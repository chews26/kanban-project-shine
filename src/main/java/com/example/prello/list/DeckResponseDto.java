package com.example.prello.list;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeckResponseDto {

    private Long id;
    private String title;
    private int order;
    private LocalDateTime createdAt;

    public DeckResponseDto(Long id, String title, int order, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.order = order;
        this.createdAt = createdAt;
    }

}
