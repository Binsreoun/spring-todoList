package com.sparta.springtodolist.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("User 생성자 테스트")
    void Constructor() {
        User user = new User("username", "password", UserRoleEnum.USER);
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(UserRoleEnum.USER, user.getRole());
    }

    @Test
    void getId() {
        User user = new User("username", "password", UserRoleEnum.USER);
        assertNull(user.getId());
    }

    @Test
    void getUsername() {
        User user = new User("username", "password", UserRoleEnum.USER);
        assertEquals("username", user.getUsername());

    }

    @Test
    void getPassword() {
        User user = new User("username", "password", UserRoleEnum.USER);
        assertEquals("password", user.getPassword());
    }

    @Test
    void getRole() {
        User user = new User("username", "password", UserRoleEnum.USER);
        assertEquals(UserRoleEnum.USER, user.getRole());
    }

    @Test
    void setId() {
        User user = new User("username", "password", UserRoleEnum.USER);
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void setUsername() {
        User user = new User("username", "password", UserRoleEnum.USER);
        user.setUsername("테스트2");
        assertEquals("테스트2", user.getUsername());
    }

    @Test
    void setPassword() {
        User user = new User("username", "password", UserRoleEnum.USER);
        user.setPassword("페스워드");
        assertEquals("페스워드", user.getPassword());
    }

    @Test
    void setRole() {
        User user = new User("username", "password", UserRoleEnum.USER);
        user.setRole(UserRoleEnum.ADMIN);
        assertEquals(UserRoleEnum.ADMIN, user.getRole());
    }
}