package com.example.prello.card.dto;

import com.example.prello.card.entity.Card;
import com.example.prello.comment.Comment;
import lombok.Getter;

import java.util.List;


@Getter
public class CardDetailResponseDto extends CardResponseDto {

    public CardDetailResponseDto(Card card, List<Comment> comments) {
        super(card.getId(), card.getTitle(), card.getDescription(), card.getEndAt(), card.getCreatedAt(), card.getUpdatedAt());
        this.comments = comments;
    }

    private final List<Comment> comments;
}
