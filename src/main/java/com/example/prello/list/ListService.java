package com.example.prello.list;

import com.example.prello.board.Board;
import com.example.prello.board.BoardService;
import com.example.prello.workspace.Workspace;
import com.example.prello.workspace.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListService {

    private final WorkspaceService workspaceService;
    private final BoardService boardService;
    private final ListRepository listRepository;

    public static BoardListResponseDto createList(Long workspaceId, Long boardId, BoardListRequestDto boardListRequestDto) {
        Workspace workspace = workspaceService.findById(workspaceId)
                .orElseThrow(() -> new IllegalArgumentException("워크스페이스를 찾을 수 없습니다."));

        Board board = boardService.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("보드를 찾을 수 없습니다."));

        BoardList boardList = new BoardList(boardListRequestDto.getTitle(),"0", board);

        return new BoardListResponseDto(
                boardList.getId(),
                boardList.getTitle(),
                boardList.getOrder(),
                boardList.getCreatedAt()
        );
    }
}
