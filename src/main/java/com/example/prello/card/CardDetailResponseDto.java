package com.example.prello.card;

import com.example.prello.comment.Comment;
import lombok.Getter;

import java.util.List;


@Getter
public class CardDetailResponseDto extends CardResponseDto {

    private final List<Comment> comments;

    public CardDetailResponseDto(Card card, List<Comment> comments) {
        super(card);
        this.comments = comments;
    }
}
