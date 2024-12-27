package com.example.prello.user.enums;

import lombok.Getter;

@Getter
public enum Auth {
    USER("user"),
    ADMIN("admin");

    private String name;

    Auth(String name) {
        this.name = name;
    }

    public static Auth of(String authName) throws IllegalArgumentException {
        for (Auth auth : values()) {
            if (auth.getName().equals(authName)) {
                return auth;
            }
        }

        throw new IllegalArgumentException("해당하는 이름의 권한을 찾을 수 없습니다");
    }
}
