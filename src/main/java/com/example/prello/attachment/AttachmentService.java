package com.example.prello.attachment;

import com.example.prello.card.entity.Card;
import com.example.prello.card.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

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
     * 카드에 첨부파일을 등록
     *
     * @param cardId       카드 식별자
     * @param attachmentId 첨부파일 식별자
     */
    @Transactional
    public void addAttachmentToCard(Long cardId, Long attachmentId) {
        Card findCard = cardService.findCardByIdOrElseThrow(cardId);
        Attachment findAttachment = findAttachmentByIdOrElseThrow(attachmentId);

        findAttachment.addAttachmentToCard(findCard);
    }

    public Attachment findAttachmentByIdOrElseThrow(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
