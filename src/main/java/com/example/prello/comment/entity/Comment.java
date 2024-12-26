package com.example.prello.comment.entity;

import com.example.prello.card.Card;
import com.example.prello.common.BaseEntity;
import com.example.prello.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "`comment`")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    public Comment() {}

    @Builder
    public Comment(String content, User user, Card card) {
        this.content = content;
        this.user = user;
        this.card = card;
    }
}
