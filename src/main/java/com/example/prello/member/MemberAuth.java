package com.example.prello.member;

import lombok.Getter;

@Getter
public enum MemberAuth {
    WORKSPACE("workspace") {
        @Override
        public void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException {
            if (newAuth == BOARD || newAuth == READ_ONLY) {
                throw new IllegalAccessException("WORKSPACE 소유자의 권한을 변경할 수 없습니다.");
            }
        }
    },
    BOARD("board") {
        @Override
        public void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException {
            if (newAuth == WORKSPACE) {
                throw new IllegalAccessException("WORKSPACE 권한으로 변경할 수 없습니다.");
            }
        }
    },
    READ_ONLY("read-only") {
        @Override
        public void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException {
            if (newAuth == WORKSPACE) {
                throw new IllegalAccessException("WORKSPACE 권한으로 변경할 수 없습니다");
            }
        }
    };

    public abstract void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException;

    private final String memberAuth;

    MemberAuth(String auth) {
        this.memberAuth = auth;
    }

    public static MemberAuth of(String authName) {
        for (MemberAuth auth : MemberAuth.values()) {
            if (auth.getMemberAuth().equals(authName)) {
                return auth;
            }
        }
        // todo : 예외처리 controller mapping 필요
        // 400 INVALID_AUTH 해당하는 권한을 찾을 수 없습니다.
        throw new IllegalArgumentException("해당하는 권한을 찾을 수 없습니다. : " + authName);
    }
}