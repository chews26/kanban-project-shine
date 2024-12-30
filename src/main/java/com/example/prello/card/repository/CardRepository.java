package com.example.prello.card.repository;

import com.example.prello.card.entity.Card;
import com.example.prello.exception.CardErrorCode;
import com.example.prello.exception.CustomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    /**
     * id로 단일 카드를 찾고, 없을 시 커스텀 예외
     *
     * @param id 카드 식별자
     * @return 카드
     */
    default Card findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(CardErrorCode.CARD_NOT_FOUND));
    }

//    @Query(
//            "SELECT c FROM Card c "
//            + "JOIN FETCH c.deck d "
//            + "JOIN FETCH d.board b "
//            + "JOIN FETCH b.workspace w "
//            + "WHERE c.id = :cardId"
//    )
//    Optional<Card> checkPathVariableAndReturn(Long workspaceId, Long boardId, Long deckId, @Param("cardId") Long cardId);
}
