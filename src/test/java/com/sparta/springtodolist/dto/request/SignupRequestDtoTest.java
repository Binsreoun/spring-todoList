package com.sparta.springtodolist.dto.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SignupRequestDtoTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
        .password("12345678").build();

    @BeforeAll
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    void getUsername() {
        assertEquals("test123", signupRequestDto.getUsername());
    }

    @DisplayName("Validation username 에러")
    @Test
    void getUsername_fail1() {
        SignupRequestDto signupRequestDtoErr = SignupRequestDto.builder()
            .username("test1234564645645646")
            .password("12345678").build();
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(
            signupRequestDtoErr);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void getPassword() {
        assertEquals("12345678", signupRequestDto.getPassword());
    }

    @DisplayName("Validation password 에러")
    @Test
    void getPassword_fail1() {
        SignupRequestDto signupRequestDtoErr = SignupRequestDto.builder()
            .username("test123")
            .password("123").build();
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(
            signupRequestDtoErr);

        assertThat(violations).isNotEmpty();
    }
    
}