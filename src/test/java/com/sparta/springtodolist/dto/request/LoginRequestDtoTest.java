package com.sparta.springtodolist.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoginRequestDtoTest {

    @Test
    void builder() {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder().username("test123")
            .password("12345678").build();
        assertEquals("test123", loginRequestDto.getUsername());
        assertEquals("12345678", loginRequestDto.getPassword());
    }

    @Test
    void getUsername() {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder().username("test123")
            .password("12345678").build();
        assertEquals("test123", loginRequestDto.getUsername());
    }

    @Test
    void getPassword() {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder().username("test123")
            .password("12345678").build();
        assertEquals("12345678", loginRequestDto.getPassword());
    }

}