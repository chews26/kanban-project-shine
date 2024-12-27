package com.example.prello.security.session;

import com.example.prello.common.SessionName;
import com.example.prello.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor

public class SessionUtils {
    private final HttpSession session;

    // 이름 가져오기
    public String getLoginUserName() {
        String name = (String) session.getAttribute(SessionName.USER_AUTH);
        if (name == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        return name;
    }

    // 이메일 가져오기
    public String getLoginUserEmail() {
        String email = (String)session.getAttribute(SessionName.USER_AUTH);

        if (email == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        return email;
    }

    // ID 가져오기
    public Long getLoginUserId() {
        Long userId = (Long) session.getAttribute(SessionName.USER_ID);
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }
        return userId;
    }

    // 리소스 소유자와 현재 로그인 사용자 비교하여 권한 확인
    public void checkAuthorization(User resourceOwner) {
        String loginEmail = getLoginUserEmail(); // 현재 로그인된 사용자 이메일
        String ownerEmail = resourceOwner.getEmail(); // 리소스 소유자의 이메일

        if (!loginEmail.equals(ownerEmail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
        }
    }

    // 세션 데이터 업데이트
    public <T> void updateSession(String key, T value) {
        session.setAttribute(key, value);
    }

    // 세션 초기화
    public void clearSession() {
        session.invalidate();
    }
}