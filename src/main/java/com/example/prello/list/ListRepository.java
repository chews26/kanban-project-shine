package com.example.prello.list;

import com.example.prello.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListRepository extends JpaRepository<BoardList, Long> {
    List<BoardList> findByBoard(Board board);
}
