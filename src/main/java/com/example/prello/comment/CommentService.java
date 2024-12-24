package com.example.prello.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 수정사항 만들기
    private void checkPathVariableIds(Long workspaceId, Long boardId, Long listId, Long cardId) {
    }
}
