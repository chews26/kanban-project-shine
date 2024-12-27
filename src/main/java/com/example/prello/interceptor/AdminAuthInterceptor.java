package com.example.prello.interceptor;

import com.example.prello.user.dto.AuthenticationDto;
import com.example.prello.user.enums.Auth;
import com.example.prello.common.SessionName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        AuthenticationDto auth = (AuthenticationDto) session.getAttribute(SessionName.USER_AUTH);

        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
        }

        if (auth.getAuth() != Auth.ADMIN) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, " ADMIN 권한이 필요합니다."
            );
        }

        return true;
    }
}