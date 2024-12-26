package com.example.prello.list;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/boards/{boardId}/lists")
public class DeckController {

    private final DeckService deckService;

    //리스트 생성
    @PostMapping
    public ResponseEntity<DeckResponseDto> createBoardList(
        @PathVariable Long workspaceId,
        @PathVariable Long boardId,
        @Valid @RequestBody DeckRequestDto dto) {

        DeckResponseDto deckResponseDto = deckService.createDeck(workspaceId, boardId, dto);
        return new ResponseEntity<>(deckResponseDto, HttpStatus.CREATED);
        //return DeckService.createList(workspaceId, boardId, boardListRequestDto);
    }

    //리스트 제목 수정
    @PutMapping("/{listId}")
    public ResponseEntity<DeckResponseDto> updateDeckTitle(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long id,
            @Valid @RequestBody DeckRequestDto dto) {

             DeckResponseDto deckResponseDto = deckService.updateDeckTitle(workspaceId, boardId, id, dto);
             return new ResponseEntity<>(deckResponseDto, HttpStatus.OK);
    }

    //리스트 순서 수정
    @PutMapping("/{listId}/order")
    public ResponseEntity<DeckResponseDto> updateDeckOrder(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long id,
            @Valid @RequestBody DeckRequestDto dto){

            DeckResponseDto deckResponseDto = deckService.updateDexkOrder(workspaceId, boardId, id, dto);
            return new ResponseEntity<>(deckResponseDto, HttpStatus.OK);
    }
    //리스트 삭제
    @DeleteMapping("/{listId}")
    public ResponseEntity<DeckResponseDto> deleteDeck(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long id){

            deckService.deleteDeck(workspaceId, boardId, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
