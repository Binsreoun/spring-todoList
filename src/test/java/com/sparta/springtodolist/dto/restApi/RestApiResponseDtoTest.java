package com.sparta.springtodolist.dto.restApi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RestApiResponseDtoTest {

    @Test
    @DisplayName("RestApiExceptionDto 생성자 테스트")
    void Constructor() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        assertEquals("테스트 메세지", restApiResponseDto.getMessage());
        assertEquals(200, restApiResponseDto.getStatusCode());
        assertEquals(user, restApiResponseDto.getData());
    }

    @Test
    void getMessage() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        assertEquals("테스트 메세지", restApiResponseDto.getMessage());
    }

    @Test
    void getStatusCode() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        assertEquals(200, restApiResponseDto.getStatusCode());
    }

    @Test
    void getData() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        assertEquals(user, restApiResponseDto.getData());
    }

    @Test
    void setMessage() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        restApiResponseDto.setMessage("테스트");
        assertEquals("테스트", restApiResponseDto.getMessage());
    }

    @Test
    void setStatusCode() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        restApiResponseDto.setStatusCode(201);
        assertEquals(201, restApiResponseDto.getStatusCode());
    }

    @Test
    void setData() {
        User user = new User("username", "password", UserRoleEnum.USER);
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto("테스트 메세지", 200, user);
        User user2 = new User("username2", "password", UserRoleEnum.ADMIN);
        restApiResponseDto.setData(user2);
        assertEquals(user2, restApiResponseDto.getData());
    }
}