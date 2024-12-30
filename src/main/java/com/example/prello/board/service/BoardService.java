package com.example.prello.board.service;

import com.example.prello.board.dto.BoardRequestDto;
import com.example.prello.board.dto.BoardResponseDto;
import com.example.prello.board.entity.Board;
import com.example.prello.board.repository.BoardRepository;
import com.example.prello.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceService workspaceService;

    // 보드 생성
    public BoardResponseDto createBoard(Long workspaceId, BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getBgColor(), boardRequestDto.getBgColor());
        boardRepository.save(board);
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .bgColor(board.getBg_color())
                .bgColor(board.getBg_color())
                .createdAt(board.getCreatedAt())
                .build();
    }

    // 보드 수정
    // todo worksapce + board id fetch join 필요
    public BoardResponseDto updateBoard(Long workspaceId, Long id, BoardRequestDto boardRequestDto) {
        workspaceService.findByIdOrElseThrow(workspaceId);
        Board board = findByIdOrElseThrow(id);

        Board update = board.update(boardRequestDto.getTitle(), boardRequestDto.getBgColor(), boardRequestDto.getBgImage());
        boardRepository.save(board);

        return BoardResponseDto.toDto(update);
    }

    // 보드 상세 조회
    // todo worksapce + board id fetch join 필요
    public BoardResponseDto getBoard(Long workspaceId, Long id) {
        workspaceService.findByIdOrElseThrow(workspaceId);
        Board board = findByIdOrElseThrow(id);
        return BoardResponseDto.toDto(board);
    }

    // 보드 삭제
    // todo worksapce + board id fetch join 필요
    public String deleteBoard(Long workspaceId, Long id) {
        workspaceService.findByIdOrElseThrow(workspaceId);
        findByIdOrElseThrow(id);
        boardRepository.deleteById(id);
        return "보드가 삭제되었습니다.";
    }

    // 보드 findById
    public Board findByIdOrElseThrow(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
