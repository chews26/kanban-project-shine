package com.example.prello.comment.dto;

import com.example.prello.comment.entity.Comment;
import com.example.prello.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final String name;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
        comment.getId(),
        comment.getUser().getName(),
        comment.getContent(),
        comment.getCreatedAt(),
        comment.getUpdatedAt()
        );
    }
}
