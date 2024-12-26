package com.example.prello.list;

import com.example.prello.board.Board;
import com.example.prello.board.BoardService;
import com.example.prello.workspace.Workspace;
import com.example.prello.workspace.WorkspaceService;
import jakarta.validation.Valid;
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

    //리스트 생성
    @Transactional
    public DeckResponseDto createDeck(Long workspaceId, Long boardId, DeckRequestDto dto) {
        Board board = checkPathVariable(workspaceId, boardId);

        Deck deck = Deck.builder()
                .title(dto.getTitle())
                .order(0)
                .board(board)
                .build();

        //저장
        Deck savedDeck = deckRepository.save(deck);

        return DeckResponseDto.toDto(savedDeck);
    }

    //리스트 제목 수정
    @Transactional
    public DeckResponseDto updateDeckTitle(Long workspaceId, Long boardId, Long id, @Valid DeckRequestDto dto) {
        checkPathVariable(workspaceId, boardId);

        Deck findDeck = findDeckById(id);
        findDeck.updateDeckTitle(dto.getTitle());

        return DeckResponseDto.toDto(findDeck);
    }

    //리스트 순서 수정
    @Transactional
    public DeckResponseDto updateDexkOrder(Long workspaceId, Long boardId, Long id, @Valid DeckRequestDto dto) {
        checkPathVariable(workspaceId, boardId);

        Deck findDeck = findDeckById(id);
        int currentOrder = findDeck.getOrder();

        //순서 변경 로직 작성해야 함

        return DeckResponseDto.toDto(findDeck);
    }

    //리스트 삭제
    @Transactional
    public void deleteDeck(Long workspaceId, Long boardId, Long id) {
        checkPathVariable(workspaceId, boardId);

        Deck findDeck = findDeckById(id);
        deckRepository.delete(findDeck);
    }

    //리스트 id로 조회
    public Deck findDeckById(Long id) {
        return deckRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리스트를 찾을 수 없습니다."));
    }

    //workspace, board 검증 및 board 반환
    private Board checkPathVariable(Long workspaceId, Long boardId) {
        //workspace 검증
        workspaceService.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("워크스페이스를 찾을 수 없습니다."));

        //board 검증
        return boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));
    }
}
