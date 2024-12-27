package com.example.prello.attachment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class AttachmentForm {

    @NotEmpty
    private final String fileName;

    @NotNull
    private final MultipartFile attachFile;
}
