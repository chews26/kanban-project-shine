package com.example.prello.list;

import com.example.prello.board.Board;
import com.example.prello.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "`boardlist`")
public class Deck extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int order;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public Deck() {}

    public Deck(String title, int order, Board board) {
        this.title = title;
        this.order = order;
        this.board = board;
    }
}
