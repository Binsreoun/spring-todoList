package com.sparta.springtodolist.dto.request;

import lombok.Builder;
import lombok.Getter;


@Getter
public class LoginRequestDto {

    private String username;
    private String password;

    public LoginRequestDto(){

    }

    @Builder
    public LoginRequestDto(String username,String password){
        this.username = username;
        this.password = password;
    }

}
