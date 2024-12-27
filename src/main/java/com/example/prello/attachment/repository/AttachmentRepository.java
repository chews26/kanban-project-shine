package com.example.prello.attachment.repository;

import com.example.prello.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    /**
     * 카드에 첨부된 모든 첨부파일을 찾음
     *
     * @param cardId 카드 식별자
     * @return 첨부파일 목록
     */
    @Query("SELECT a FROM Attachment a JOIN FETCH a.card c WHERE c.id = :cardId")
    List<Attachment> findAllByCardId (Long cardId);
}
