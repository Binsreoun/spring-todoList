package com.sparta.springtodolist.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {

    private String username;
    private String password;
}
