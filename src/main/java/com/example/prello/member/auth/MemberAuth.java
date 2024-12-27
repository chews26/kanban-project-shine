package com.example.prello.member.auth;

import lombok.Getter;

@Getter
public enum MemberAuth {
    WORKSPACE("workspace") {
        @Override
        public void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException {
        }
    },
    BOARD("board") {
        @Override
        public void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException {
        }
    },
    READ_ONLY("read-only") {
        @Override
        public void validateAuthChange(MemberAuth newAuth) throws IllegalAccessException {
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