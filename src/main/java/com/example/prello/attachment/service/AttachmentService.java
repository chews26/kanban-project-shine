package com.example.prello.attachment.service;

import com.example.prello.attachment.FileStore;
import com.example.prello.attachment.dto.AttachmentForm;
import com.example.prello.attachment.dto.AttachmentResponseDto;
import com.example.prello.attachment.entity.Attachment;
import com.example.prello.attachment.repository.AttachmentRepository;
import com.example.prello.card.entity.Card;
import com.example.prello.card.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final FileStore fileStore;
    private final CardService cardService;

    /**
     * 첨부파일 등록 서비스 메서드
     *
     * @param form 첨부파일 포함된 정보 폼
     * @return 첨부파일 정보
     */
    @Transactional
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

    /**
     * 카드 내 첨부파일 조회
     *
     * @param cardId 카드 식별자
     * @return 카드 내 첨부파일 목록
     */
    public List<AttachmentResponseDto> findAttachment(Long cardId) {
        List<Attachment> attachments = attachmentRepository.findAllByCardId(cardId);
        return attachments.stream().map(AttachmentResponseDto::toDto).toList();
    }

    /**
     * 첨부파일 다운로드
     *
     * @param id 첨부파일 식별자
     * @return 첨부파일 리소스
     */
    public UrlResource downloadAttachment(Long id) {
        Attachment findAttachment = findByIdOrElseThrow(id);
        String storeFileName = findAttachment.getStoreFileName();
        try {
            return new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        } catch (MalformedURLException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 다운로드에 실패했습니다.");
        }
    }

    /**
     * 첨부파일 삭제
     *
     * @param id 첨부파일 식별자
     */
    @Transactional
    public void deleteAttachment(Long id) {
        Attachment findAttachment = findByIdOrElseThrow(id);
        attachmentRepository.delete(findAttachment);
    }

    /**
     * 첨부파일 다운로드 헤더 설정용 content disposition 생성
     *
     * @param id 첨부파일 식별자
     * @return content disposition 문자열
     */
    public String createContentDisposition(Long id) {
        Attachment findAttachment = findByIdOrElseThrow(id);
        return "attachment; filename=\"" + findAttachment.getUploadFileName() + "\"";
    }

    /**
     * 카드에 첨부파일을 등록
     *
     * @param cardId       카드 식별자
     * @param attachmentId 첨부파일 식별자
     */
    @Transactional
    public void addAttachmentToCard(Long cardId, Long attachmentId) {
        Card findCard = cardService.findByIdOrElseThrow(cardId);
        Attachment findAttachment = findByIdOrElseThrow(attachmentId);

        findAttachment.addAttachmentToCard(findCard);
    }

    /**
     * 레포지토리를 통해 Attachment 를 찾음
     *
     * @param id 첨부파일 식별자
     * @return Attachment
     */
    public Attachment findByIdOrElseThrow(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
