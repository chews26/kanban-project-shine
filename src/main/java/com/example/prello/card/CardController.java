package com.example.prello.card;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/boards/{boardId}/lists/{listId}/cards")
public class CardController {

    private final CardService cardService;

    /**
     * 카드 생성
     *
     * @param workspaceId 워크스페이스 식별자
     * @param boardId     보드 식별자
     * @param listId      리스트 식별자
     * @param dto         {@link CardRequestDto}
     * @return 201 CREATED
     */
    @PostMapping
    public ResponseEntity<CardResponseDto> createCard(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @Valid @RequestBody CardRequestDto dto
    ) {

        CardResponseDto cardResponseDto = cardService.createCard(workspaceId, boardId, listId, dto);

        return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
    }

    /**
     * 카드 (부분) 수정
     *
     * @param id  카드 식별자
     * @param dto 수정할 내용이 담긴 nullable dto
     * @return 200 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CardResponseDto> updateCard(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long id,
            @RequestBody CardRequestDto dto
    ) {

        CardResponseDto cardResponseDto = cardService.updateCard(workspaceId, boardId, listId, id, dto);
        return ResponseEntity.ok(cardResponseDto);
    }

    /**
     * 카드 상세 조회
     *
     * @param id 카드 식별자
     * @return 댓글 포함한 카드 정보 dto
     */
    @GetMapping("/{id}")
    public ResponseEntity<CardDetailResponseDto> findCard(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long id
    ) {

        CardDetailResponseDto cardDetailResponseDto = cardService.findCard(workspaceId, boardId, listId, id);
        return ResponseEntity.ok(cardDetailResponseDto);
    }

    /**
     * 카드 삭제
     *
     * @param id 카드 식별자
     * @return 삭제 확인 문장
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCard(
            @PathVariable Long workspaceId,
            @PathVariable Long boardId,
            @PathVariable Long listId,
            @PathVariable Long id
    ) {

        cardService.deleteCard(workspaceId, boardId, listId, id);
        return ResponseEntity.ok("카드가 삭제되었습니다.");
    }
}
