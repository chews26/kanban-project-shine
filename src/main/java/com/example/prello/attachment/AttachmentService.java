package com.example.prello.attachment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final FileStore fileStore;

    /**
     * 첨부 파일 등록 서비스 메서드
     *
     * @param form 첨부 파일 포함된 정보 폼
     * @return
     */
    public AttachmentResponseDto createAttachment(AttachmentForm form) {
        // TODO: 커스텀 예외
        Attachment attachment;
        try {
            attachment = fileStore.storeFile(form.getAttachFile());
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Attachment savedAttachment = attachmentRepository.save(attachment);
        return AttachmentResponseDto.toDto(savedAttachment);
    }
}
