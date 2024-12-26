package com.example.prello.user.controller;

import com.example.prello.user.dto.LoginRequestDto;
import com.example.prello.user.dto.UserResponseDto;
import com.example.prello.user.entity.User;
import com.example.prello.user.service.UserService;
import com.example.prello.user.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody SignUpRequestDto reqeustDto) {
        UserResponseDto responseDto = userService.signUp(reqeustDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {
        userService.login(requestDto);
        HttpSession session = request.getSession();
        session.setAttribute("email", requestDto.getEmail());

        return new ResponseEntity<>("로그인을 성공했습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<String> delete(@SessionAttribute("email") String email, @RequestBody String password, HttpServletRequest request) {
        userService.delete(email, password);
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("탈퇴가 완료되었습니다.", HttpStatus.OK);
    }

}
