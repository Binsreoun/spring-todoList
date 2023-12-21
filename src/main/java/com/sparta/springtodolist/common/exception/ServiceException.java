package com.sparta.springtodolist.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorCode code;

    public ServiceException(ErrorCode code) {
        this.code = code;
    }
}