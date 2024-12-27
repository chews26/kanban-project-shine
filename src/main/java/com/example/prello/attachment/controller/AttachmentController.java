package com.example.prello.attachment.controller;

import com.example.prello.attachment.dto.AttachmentForm;
import com.example.prello.attachment.dto.AttachmentResponseDto;
import com.example.prello.attachment.service.AttachmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    /**
     * 첨부 파일 등록
     *
     * @param form 첨부 파일 폼
     * @return 201 CREATED
     */
    @PostMapping
    public ResponseEntity<AttachmentResponseDto> createAttachment(
            @Valid @ModelAttribute AttachmentForm form
    ) {

        AttachmentResponseDto attachmentResponseDto = attachmentService.createAttachment(form);
        return new ResponseEntity<>(attachmentResponseDto, HttpStatus.CREATED);
    }

    /**
     * 카드 내 첨부파일 조회
     *
     * @param cardId 카드 식별자
     * @return 200 OK
     */
    @GetMapping("/{cardId}")
    public ResponseEntity<List<AttachmentResponseDto>> findAttachment(
            @PathVariable Long cardId
    ) {

        List<AttachmentResponseDto> attachmentResponseDtos = attachmentService.findAttachment(cardId);
        return new ResponseEntity<>(attachmentResponseDtos, HttpStatus.OK);
    }

    /**
     * 첨부파일 다운로드
     *
     * @param id 첨부파일 식별자
     * @return 200 OK
     */
    @GetMapping("/{id}/download")
    public ResponseEntity<UrlResource> downloadAttachment(
            @PathVariable Long id
    ) {

        UrlResource resource = attachmentService.downloadAttachment(id);
        String contentDisposition = attachmentService.createContentDisposition(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    /**
     * 첨부파일 삭제
     *
     * @param id 첨부파일 식별자
     * @return 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttachment(
            @PathVariable Long id
    ) {

        attachmentService.deleteAttachment(id);
        return new ResponseEntity<>("첨부파일이 삭제되었습니다.", HttpStatus.OK);
    }
}
