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



}
