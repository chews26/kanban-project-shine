package com.example.prello.user.repository;

import com.example.prello.user.entity.User;
import com.example.prello.user.enums.UserErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(()-> new IllegalArgumentException(UserErrorCode.NOT_FOUND_USER.getMessage()));
    }

    User findByEmailAndDeletedAtIsNotNull(String email);
}
