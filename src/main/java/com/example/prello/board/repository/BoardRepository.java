package com.example.prello.board.repository;


import com.example.prello.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b JOIN FETCH b.workspace w WHERE b.id = :boardId AND w.id = :workspaceId")
    Optional<Board> findByWorkspaceIdAndBoardId(Long workspaceId, Long boardId);

}
