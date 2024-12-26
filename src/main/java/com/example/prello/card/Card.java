package com.example.prello.card;

import com.example.prello.common.BaseEntity;
import com.example.prello.list.List;
import com.example.prello.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "`card`")
public class Card extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    private List list;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime endAt;

    public Card(){}

    public Card(List list, String title, String description, LocalDateTime endAt) {
        this.list = list;
        this.title = title;
        this.description = description;
        this.endAt = endAt;
    }

    /**
     * 담당자 추가
     *
     * @param user 담당자(멤버)
     */
    public void addAssignees(User user) {
        this.user = user;
    }

    /**
     * 카드 업데이트 메서드. null 이 아니면 값 변경.
     *
     * @param title 변경할 제목
     * @param description 변경할 설명
     * @param endAt 변경할 마감일
     */
    public void updateCard(String title, String description, LocalDateTime endAt) {
        if(title != null) {
            this.title = title;
        }

        if(description != null) {
            this.description = description;
        }

        if(endAt != null) {
            this.endAt = endAt;
        }
    }
}
