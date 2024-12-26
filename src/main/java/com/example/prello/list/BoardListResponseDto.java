package com.example.prello.list;

import lombok.Getter;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {

    private Long id;
    private String title;
    private int order;
    private LocalDateTime createdAt;

    public BoardListResponseDto(Long id, String title, int order, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.order = order;
        this.createdAt = createdAt;
    }

}
