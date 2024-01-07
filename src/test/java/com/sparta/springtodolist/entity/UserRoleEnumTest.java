package com.sparta.springtodolist.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRoleEnumTest {

    @Test
    void getAuthority() {
        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User("username", "password", role);
        UserRoleEnum userRole = user.getRole();
        assertEquals(userRole,role);
    }

    @Test
    void values() {
        UserRoleEnum role = UserRoleEnum.USER;
        User user = new User("username", "password", role);
        UserRoleEnum userRole = user.getRole();
        assertEquals(userRole.getAuthority(),role.getAuthority());
        assertEquals(userRole.toString(),role.toString());
        assertEquals(userRole.name(),role.name());
    }

    @Test
    void Authority() {
        UserRoleEnum.Authority role = new UserRoleEnum.Authority();

    }
}