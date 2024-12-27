package com.example.prello.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board findByIdOrElseThrow(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
