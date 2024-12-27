package com.example.prello.board.dto;

import com.example.prello.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;

    private final String title;

    private final String bgColor;

    private final String bgImage;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getBg_color(),
                board.getBg_image(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}
