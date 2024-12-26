package com.example.prello.list;

import com.example.prello.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    //보드에서 리스트 조회
    List<Deck> findByBoard(Board board);
}
