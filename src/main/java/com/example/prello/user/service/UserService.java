package com.example.prello.user.service;

import com.example.prello.user.dto.DeleteRequestDto;
import com.example.prello.user.dto.LoginRequestDto;
import com.example.prello.user.dto.SignUpRequestDto;
import com.example.prello.user.dto.UserResponseDto;
import com.example.prello.user.entity.User;
import com.example.prello.user.enums.UserErrorCode;
import com.example.prello.user.repository.UserRepository;
import com.example.prello.user.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto signUp(SignUpRequestDto requestDto) {

        Optional<User> findUser = userRepository.findByEmail(requestDto.getEmail());
        if(findUser.isPresent()) {
            if (findUser.get().getDeletedAt() != null) {
                throw new IllegalArgumentException(UserErrorCode.DELETED_ACCOUNT.getMessage());
            }
            throw new IllegalArgumentException(UserErrorCode.DUPLICATED_EMAIL.getMessage());
        }

        String encodedPassword = PasswordEncoder.encode(requestDto.getPassword());

        User user = new User(requestDto.getEmail(), requestDto.getName(), encodedPassword, requestDto.getAuth());
        User savedUser = userRepository.save(user);
        return UserResponseDto.toDto(savedUser);
    }

    public User login(LoginRequestDto requestDto) {
        User findUser = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException(UserErrorCode.NOT_FOUND_USER.getMessage()));

        if(findUser.getDeletedAt()!=null) {
            throw new IllegalArgumentException(UserErrorCode.DELETED_ACCOUNT.getMessage());
        }
        if(!PasswordEncoder.matches(requestDto.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException(UserErrorCode.INVALID_PASSWORD.getMessage());
        }

        return findUser;
    }

    public void delete(Long userId, DeleteRequestDto requestDto) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException(UserErrorCode.NOT_FOUND_USER.getMessage()));

        if(!PasswordEncoder.matches(requestDto.getPassword(), findUser.getPassword())){
            throw new IllegalArgumentException(UserErrorCode.INVALID_PASSWORD.getMessage());
        }

        findUser.deleteSoftly();
        userRepository.save(findUser);
    }
}
