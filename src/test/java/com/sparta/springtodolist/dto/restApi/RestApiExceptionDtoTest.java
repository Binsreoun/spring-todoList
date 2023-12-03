package com.sparta.springtodolist.dto.restApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestApiExceptionDtoTest {

    @Test
    @DisplayName("RestApiExceptionDto 생성자 테스트")
    void ConstructorTest() {
        RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto("테스트 메세지", 200);

        assertEquals("테스트 메세지", restApiExceptionDto.getErrorMessage());
        assertEquals(200, restApiExceptionDto.getStatusCode());
    }
}