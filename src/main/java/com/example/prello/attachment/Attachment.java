package com.example.prello.attachment;

import com.example.prello.card.entity.Card;
import com.example.prello.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder(builderClassName = "Builder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "`attachment`")
public class Attachment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private String fileName;

    private String fileUrl;

    @Column(length = 10)
    @Size(max = 10, message = "파일 형식은 10자 이내여야 합니다.")
    private String fileType;

    public Attachment() {
    }

    public Attachment(String fileName, String fileUrl, String fileType) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

    public void addAttachmentToCard(Card card) {
        this.card = card;
    }
}
