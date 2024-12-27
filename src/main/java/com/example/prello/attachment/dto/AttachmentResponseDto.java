package com.example.prello.attachment.dto;

import com.example.prello.attachment.entity.Attachment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AttachmentResponseDto {

    private final Long id;

    private final String filename;

    public static AttachmentResponseDto toDto(Attachment attachment) {
        return new AttachmentResponseDto(
                attachment.getId(),
                attachment.getUploadFileName()
        );
    }
}
