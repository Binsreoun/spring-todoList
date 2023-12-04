package com.sparta.springtodolist.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoginRequestDtoTest {

    LoginRequestDto loginRequestDto = LoginRequestDto.builder().username("test123")
        .password("12345678").build();

    @Test
    void builder() {
        assertEquals("test123", loginRequestDto.getUsername());
        assertEquals("12345678", loginRequestDto.getPassword());
    }

    @Test
    void getUsername() {
        assertEquals("test123", loginRequestDto.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("12345678", loginRequestDto.getPassword());
    }

}