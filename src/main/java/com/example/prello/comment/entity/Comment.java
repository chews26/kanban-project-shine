package com.example.prello.comment.entity;

import com.example.prello.card.entity.Card;
import com.example.prello.common.BaseEntity;

import com.example.prello.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.example.prello.comment.entity.QComment.comment;

@Entity
@Getter
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "`comment`")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    public Comment() {}

    public Comment( String name, String content, User user, Card card) {
        this.name = name;
        this.content = content;
        this.user = user;
        this.card = card;
    }

    public Comment updateComment(String content) {
        if(comment != null) {
            this.content = content;
        }
        return this;
    }
}
