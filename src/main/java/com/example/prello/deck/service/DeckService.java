package com.example.prello.deck.service;

import com.example.prello.board.entity.Board;
import com.example.prello.board.service.BoardService;
import com.example.prello.deck.dto.DeckUpdateRequestDto;
import com.example.prello.deck.repository.DeckRepository;
import com.example.prello.deck.dto.DeckRequestDto;
import com.example.prello.deck.dto.DeckResponseDto;
import com.example.prello.deck.entity.Deck;
import com.example.prello.exception.CustomException;
import com.example.prello.exception.DeckErrorCode;
import com.example.prello.member.auth.MemberPermissionService;
import com.example.prello.workspace.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final WorkspaceService workspaceService;
    private final BoardService boardService;
    private final DeckRepository deckRepository;
    private final MemberPermissionService memberPermissionService;

    //리스트 생성
    @Transactional
    public DeckResponseDto createDeck(Long workspaceId, Long boardId, DeckRequestDto dto) {
        memberPermissionService.validateBoardAccess(workspaceId);
        checkPathVariable(workspaceId, boardId);

        Board board = boardService.findByIdOrElseThrow(boardId);

        int newOrder = deckRepository.findMaxOrderByBoardId(boardId).orElse(0) + 1;

        Deck deck = Deck.builder()
                .title(dto.getTitle())
                .order(newOrder)
                .board(board)
                .build();

        //저장
        Deck savedDeck = deckRepository.save(deck);

        return DeckResponseDto.toDto(savedDeck);
    }

    //리스트 제목 수정
    @Transactional
    public DeckResponseDto updateDeckTitle(Long workspaceId, Long boardId, Long id, DeckUpdateRequestDto dto) {
        memberPermissionService.validateBoardAccess(workspaceId);
        checkPathVariable(workspaceId, boardId);

        Deck findDeck = findByIdOrElseThrow(id);
        findDeck.updateDeckTitle(dto.getTitle());

        return DeckResponseDto.toDto(findDeck);
    }

    //리스트 순서 수정
    @Transactional
    public DeckResponseDto updateDeckOrder(Long workspaceId, Long boardId, Long id, DeckUpdateRequestDto dto) {
        memberPermissionService.validateBoardAccess(workspaceId);
        checkPathVariable(workspaceId, boardId);

        Deck findDeck = findByIdOrElseThrow(id);
        int currentOrder = findDeck.getOrder();
        int newOrder = dto.getOrder();

        int maxOrder = deckRepository.findMaxOrderByBoardId(boardId).orElse(1);
        if (newOrder > maxOrder) {
            newOrder = maxOrder;
        }

        if (newOrder < 1) {
            newOrder = 1;
        }

        // order값 변경이 없을떄
        if (currentOrder == newOrder) {
            return DeckResponseDto.toDto(findDeck);
        }

        // order값이 변경 되었을때

        if(newOrder > currentOrder) {
            List<Deck> deckUpdate = deckRepository.findDecksInOrderRange(boardId, currentOrder + 1, newOrder);

            for (Deck deck : deckUpdate) {
                deck.updateDeckOrder(deck.getOrder() - 1);
            }
        } else {
            List<Deck> deckUpdate = deckRepository.findDecksInOrderRange(boardId, newOrder, currentOrder -1);

            for (Deck deck : deckUpdate) {
                deck.updateDeckOrder(deck.getOrder() + 1);
            }
        }

        findDeck.updateDeckOrder(newOrder);
        return DeckResponseDto.toDto(findDeck);
    }

    //리스트 삭제
    @Transactional
    public void deleteDeck(Long workspaceId, Long boardId, Long id) {
        memberPermissionService.validateBoardAccess(workspaceId);
        checkPathVariable(workspaceId, boardId);

        Deck findDeck = findByIdOrElseThrow(id);
        deckRepository.delete(findDeck);
    }

    //리스트 id로 조회
    public Deck findByIdOrElseThrow(Long id) {
        return deckRepository.findById(id)
                .orElseThrow(() -> new CustomException(DeckErrorCode.DECK_NOT_FOUND));
    }

    //workspace, board 검증 및 board 반환
    private void checkPathVariable(Long workspaceId, Long boardId) {
        //workspace 검증
        workspaceService.findByIdOrElseThrow(workspaceId);

        //board 검증
        boardService.findByIdOrElseThrow(boardId);
    }
}
