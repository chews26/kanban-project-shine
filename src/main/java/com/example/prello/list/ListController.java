package com.example.prello.list;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspaces/{workspaceId}/boards/{boardId}/lists")
public class ListController {

    private final ListService listService;

    //리스트 생성
    @PostMapping
    public BoardListResponseDto createBoardList(
        @PathVariable Long workspaceId,
        @PathVariable Long boardId,
        @RequestBody BoardListRequestDto boardListRequestDto) {
        return ListService.createList(workspaceId, boardId, boardListRequestDto);
    }
}
