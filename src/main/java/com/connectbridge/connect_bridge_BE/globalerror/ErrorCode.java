package com.connectbridge.connect_bridge_BE.globalerror;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 BAD_REQUEST: 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "no"),

    //404 NOT_FOUND: 리소스를 찾을 수 없음
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "no"),

     //405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "no"),

     //500 INTERNAL_SERVER_ERROR: 내부 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "no");

    private final HttpStatus status;
    private final String message;
}
