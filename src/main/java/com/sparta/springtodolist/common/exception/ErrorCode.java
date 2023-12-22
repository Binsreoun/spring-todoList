package com.sparta.springtodolist.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // user 1XXX
    NOT_EXIST_USER(HttpStatus.BAD_REQUEST, "1000", "사용자가 없습니다."),
    CHECK_USER(HttpStatus.BAD_REQUEST, "1001", "본인이 아닙니다."),
    // post 2XXX
    NOT_EXIST_POST(HttpStatus.NOT_FOUND, "2000", "게시글이 없습니다."),

    // comment 3XXX
    NOT_EXIST_COMMENT(HttpStatus.NOT_FOUND, "3000", "댓글이 없습니다."),
    // admin 4XXX

    // global 5XXX
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "5000", "로그인이 필요합니다."),
    TOKEN_SecurityException(HttpStatus.BAD_REQUEST,"5001","Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."),
    TOKEN_MalformedJwtException(HttpStatus.BAD_REQUEST,"5002","Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."),
    TOKEN_ExpiredJwtException(HttpStatus.BAD_REQUEST,"5003","Expired JWT token, 만료된 JWT token 입니다."),
    TOKEN_UnsupportedJwtException(HttpStatus.BAD_REQUEST,"5004","Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."),
    TOKEN_IllegalArgumentException(HttpStatus.BAD_REQUEST,"5005","JWT claims is empty, 잘못된 JWT 토큰 입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

