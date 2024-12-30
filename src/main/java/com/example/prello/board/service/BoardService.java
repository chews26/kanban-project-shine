package com.example.prello.board.service;

import com.example.prello.board.dto.BoardRequestDto;
import com.example.prello.board.dto.BoardResponseDto;
import com.example.prello.board.entity.Board;
import com.example.prello.board.repository.BoardRepository;
import com.example.prello.card.dto.CardResponseDto;
import com.example.prello.card.entity.Card;
import com.example.prello.card.repository.CardRepository;
import com.example.prello.card.service.CardService;
import com.example.prello.deck.dto.DeckResponseDto;
import com.example.prello.deck.dto.DeckResponseWithCardsDto;
import com.example.prello.deck.entity.Deck;
import com.example.prello.deck.repository.DeckRepository;
import com.example.prello.deck.service.DeckService;
import com.example.prello.workspace.entity.Workspace;
import com.example.prello.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceService workspaceService;
    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

    // 보드 생성
    public BoardResponseDto createBoard(Long workspaceId, BoardRequestDto boardRequestDto) {
        Workspace workspace = workspaceService.findByIdOrElseThrow(workspaceId);

        Board board = new Board(boardRequestDto.getTitle(), boardRequestDto.getBgColor(), boardRequestDto.getBgImage(), workspace);
        boardRepository.save(board);
        return BoardResponseDto.builder()
                .workspaceId(board.getWorkspace().getId())
                .id(board.getId())
                .title(board.getTitle())
                .bgColor(board.getBg_color())
                .bgImage(board.getBg_image())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    // 보드 수정
    public BoardResponseDto updateBoard(Long workspaceId, Long id, BoardRequestDto boardRequestDto) {

        Board board = findByWorkspaceIdAndBoardIdOrElseThrow(workspaceId, id);

        Board update = board.update(boardRequestDto.getTitle(), boardRequestDto.getBgColor(), boardRequestDto.getBgImage());
        boardRepository.save(board);

        return BoardResponseDto.builder()
                .workspaceId(board.getWorkspace().getId())
                .id(board.getId())
                .title(board.getTitle())
                .bgColor(board.getBg_color())
                .bgImage(board.getBg_image())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    // 보드 상세 조회
    public BoardResponseDto getBoard(Long workspaceId, Long boardId) {
        Board board = findByWorkspaceIdAndBoardIdOrElseThrow(workspaceId, boardId);

        List<Deck> decks = deckRepository.findByBoardId(boardId);

        List<Card> cards = cardRepository.findCardsByWorkspaceIdAndBoardId(workspaceId, boardId);

        Map<Long, List<CardResponseDto>> cardsByDeck = cards.stream()
                .collect(Collectors.groupingBy(
                        card -> card.getDeck().getId(),
                        Collectors.mapping(CardResponseDto::toDto, Collectors.toList())
                ));

        List<DeckResponseWithCardsDto> deckDtos = decks.stream()
                .map(deck -> DeckResponseWithCardsDto.toDto(
                        deck,
                        cardsByDeck.getOrDefault(deck.getId(), List.of()) // 카드가 없으면 빈 리스트 반환
                ))
                .toList();

        return BoardResponseDto.toDto(board, deckDtos);
    }

    // 보드 삭제
    public String deleteBoard(Long workspaceId, Long id) {
        findByWorkspaceIdAndBoardIdOrElseThrow(workspaceId, id);
        boardRepository.deleteById(id);
        return "보드가 삭제되었습니다.";
    }

    // 보드 findById
    public Board findByIdOrElseThrow(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Board findByWorkspaceIdAndBoardIdOrElseThrow(Long workspaceId, Long id) {
        return boardRepository.findByWorkspaceIdAndBoardId(workspaceId, id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
