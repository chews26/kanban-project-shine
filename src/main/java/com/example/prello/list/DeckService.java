package com.example.prello.list;

import com.example.prello.board.Board;
import com.example.prello.board.BoardService;
import com.example.prello.workspace.Workspace;
import com.example.prello.workspace.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final WorkspaceService workspaceService;
    private final BoardService boardService;
    private final DeckRepository listRepository;

    public static DeckResponseDto createList(Long workspaceId, Long boardId, DeckRequestDto boardListRequestDto) {
        Workspace workspace = workspaceService.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("워크스페이스를 찾을 수 없습니다."));

        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));

        Deck boardList = new Deck(boardListRequestDto.getTitle(),"0", board);

        return new DeckResponseDto(
                boardList.getId(),
                boardList.getTitle(),
                boardList.getOrder(),
                boardList.getCreatedAt()
        );
    }
}
