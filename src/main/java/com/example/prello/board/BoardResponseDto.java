package com.example.prello.board;

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
}
