package com.example.prello.comment.controller;

import com.example.prello.comment.dto.CommentRequestDto;
import com.example.prello.comment.dto.CommentResponseDto;
import com.example.prello.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/boards/{boardId}/lists/{listId}/cards/{cardId}/comments")
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @Valid @RequestBody CommentRequestDto dto) {

            CommentResponseDto commentResponseDto = commentService.createComment(workspaceId, boardId, listId, cardId, dto);
            return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }
    //댓글 수정
    @PatchMapping("{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto) {

            CommentResponseDto commentResponseDto = commentService.updateComment(workspaceId, boardId, listId, cardId, id, dto);
             return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }


    //댓글 삭제
    @DeleteMapping("{commentId}")
    public ResponseEntity<CommentResponseDto> deleteComment(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long cardId,
            @PathVariable Long id,
            @Valid @RequestBody CommentRequestDto dto) {

            commentService.deleteComment(workspaceId, boardId, listId, cardId, id, dto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
