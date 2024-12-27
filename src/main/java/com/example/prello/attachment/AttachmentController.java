package com.example.prello.attachment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/{cardId}")
    public ResponseEntity<AttachmentResponseDto> createAttachment(
            @Valid @ModelAttribute AttachmentForm form,
            @PathVariable Long cardId
    ) {

        AttachmentResponseDto attachmentResponseDto = attachmentService.createAttachment(form);
        attachmentService.addAttachmentToCard(cardId, attachmentResponseDto.getId());
        return new ResponseEntity<>(attachmentResponseDto, HttpStatus.CREATED);
    }
}
