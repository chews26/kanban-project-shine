package com.example.prello.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private void checkPathVariableIds(Long workspaceId, Long boardId, Long listId, Long cardId) {
    }
}
