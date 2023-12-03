package com.sparta.springtodolist.dto.restApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestApiResponseDtoTest {

    @Test
    @DisplayName("RestApiExceptionDto 생성자 테스트")
    void ConstructorTest() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);

        assertEquals("테스트 메세지", restApiResponseDto.getMessage());
        assertEquals(200, restApiResponseDto.getStatusCode());
        assertEquals(user, restApiResponseDto.getData());
    }

}