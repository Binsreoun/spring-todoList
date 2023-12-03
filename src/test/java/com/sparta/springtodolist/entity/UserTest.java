package com.sparta.springtodolist.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("User 생성자 테스트")
    void ConstructorTest() {
        User user = new User("username", "password", UserRoleEnum.USER);
        ;

        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(UserRoleEnum.USER, user.getRole());
    }
}