package com.example.prello.user.security.interceptor;

import com.example.prello.user.dto.AuthenticationDto;
import com.example.prello.user.enums.Auth;
import com.example.prello.user.security.constant.SessionName;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final Auth auth;

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

        if (auth.getAuth() != this.auth) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    this.auth.getName() + " 권한이 필요합니다."
            );
        }

        return true;
    }
}
