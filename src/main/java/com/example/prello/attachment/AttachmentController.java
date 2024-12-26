package com.example.prello.attachment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
