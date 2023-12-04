package com.sparta.springtodolist.dto.restApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestApiExceptionDtoTest {

    RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto("테스트 메세지", 200);

    @Test
    @DisplayName("RestApiExceptionDto 생성자 테스트")
    void ConstructorTest() {
        assertEquals("테스트 메세지", restApiExceptionDto.getErrorMessage());
        assertEquals(400, restApiExceptionDto.getStatusCode());
    }

    @Test
    void getErrorMessage() {
        assertEquals("테스트 메세지", restApiExceptionDto.getErrorMessage());
        assertNotEquals("에러", restApiExceptionDto.getErrorMessage());
    }

    @Test
    void getStatusCode() {
        assertEquals(400, restApiExceptionDto.getStatusCode());
    }

    @Test
    void setErrorMessage() {
        restApiExceptionDto.setErrorMessage("에러");
        assertEquals("에러", restApiExceptionDto.getErrorMessage());
    }

    @Test
    void setStatusCode() {
        restApiExceptionDto.setStatusCode(404);
        assertEquals(404, restApiExceptionDto.getStatusCode());
    }
}