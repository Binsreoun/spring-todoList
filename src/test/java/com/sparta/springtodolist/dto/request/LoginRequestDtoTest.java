package com.sparta.springtodolist.dto.request;

import com.sparta.springtodolist.dto.response.BoardResponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void create() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        assertNull(loginRequestDto.getUsername());
        assertNull(loginRequestDto.getPassword());
    }

    @Test
    void toStringTest() {
        String loginRequestDto = LoginRequestDto.builder().toString();
        assertEquals(loginRequestDto,"LoginRequestDto.LoginRequestDtoBuilder(username=null, password=null)");                ;
    }

}