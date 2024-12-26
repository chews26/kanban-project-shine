package com.example.prello.list;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workspaces/{workspaceId}/boards/{boardId}/lists")
public class DeckController {

    private final DeckService listService;

    //리스트 생성
    @PostMapping
    public DeckResponseDto createBoardList(
        @PathVariable Long workspaceId,
        @PathVariable Long boardId,
        @RequestBody DeckRequestDto boardListRequestDto) {
        return DeckService.createList(workspaceId, boardId, boardListRequestDto);
    }
}
