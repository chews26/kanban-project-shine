package com.example.prello.member.auth;

import com.example.prello.exception.CustomException;
import com.example.prello.exception.member.MemberErrorCode;
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
        throw new CustomException(MemberErrorCode.INVALID_AUTH);
    }
}