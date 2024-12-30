package com.example.prello.comment.service;

import com.example.prello.board.service.BoardService;
import com.example.prello.card.entity.Card;
import com.example.prello.card.repository.CardRepository;
import com.example.prello.card.service.CardService;
import com.example.prello.comment.dto.CommentRequestDto;
import com.example.prello.comment.dto.CommentResponseDto;
import com.example.prello.comment.entity.Comment;
import com.example.prello.comment.repository.CommentRepository;
import com.example.prello.common.SessionName;
import com.example.prello.deck.service.DeckService;
import com.example.prello.user.entity.User;
import com.example.prello.user.service.UserService;
import com.example.prello.workspace.service.WorkspaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final WorkspaceService workspaceService;
    private final BoardService boardService;
    private final DeckService deckService;
    private final CardRepository cardRepository;
    private final CardService cardService;
    private final UserService userService;
    private final HttpSession session;

    //댓글 생성
    @Transactional
    public CommentResponseDto createComment(Long workspaceId, Long boardId, Long listId, Long cardId, CommentRequestDto dto) {
        checkPathVariableIds(workspaceId, boardId, listId, cardId);

        // 세션에서 userId 가져오기
        Long userId = (Long) session.getAttribute(SessionName.USER_ID);
        if (userId == null) {
            throw new IllegalArgumentException("로그인 정보가 필요합니다.");
        }
        // 로그인된 유저 조회
        User user = userService.findByIdOrElseThrow(userId);

        Card card = cardRepository.findByIdOrElseThrow(cardId);

        Comment comment = Comment.builder()
                //.name(user.getName())
                .content(dto.getContent())
                .name(user.getName())
                .user(user)
                .card(card)
                .build();

        Comment savedComment = commentRepository.save(comment);

        return CommentResponseDto.toDto(savedComment);
    }


    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long workspaceId, Long boardId, Long listId, Long cardId, Long id, CommentRequestDto dto) {
        checkPathVariableIds(workspaceId, boardId, listId, cardId);

        Comment findComment = findByIdOrElseThrow(id);
        findComment.updateComment(dto.getContent());

        return CommentResponseDto.toDto(findComment);
    }


    //댓글 삭제
    @Transactional
    public void deleteComment(Long workspaceId, Long boardId, Long listId, Long cardId, Long id) {
        checkPathVariableIds(workspaceId, boardId, listId, cardId);
        Comment findComment = findByIdOrElseThrow(id);

        commentRepository.delete(findComment);
    }

    //댓글 id로 조회
    public Comment findByIdOrElseThrow(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
    }

    //workspace, board 검증 및 board 반환
    private void checkPathVariableIds(Long workspaceId, Long boardId, Long deckId, Long cardId) {
        //workspace 검증
        workspaceService.findByIdOrElseThrow(workspaceId);

        //board 검증
        boardService.findByIdOrElseThrow(boardId);

        //deck 검증
        deckService.findByIdOrElseThrow(deckId);

        //card 검증
        cardRepository.findByIdOrElseThrow(cardId);
    }
}
