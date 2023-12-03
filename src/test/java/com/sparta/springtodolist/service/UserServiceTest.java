package com.sparta.springtodolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import com.sparta.springtodolist.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserService userService;


    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @DisplayName("회원가입 성공")
    @Test
    void signup() {

        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(username, password, UserRoleEnum.USER);

        given(userRepository.findByUsername(username)).willReturn(Optional.empty());
        given(userRepository.save(any(User.class))).willReturn(user);
        userService.signup(signupRequestDto);
        assertThat(username).isEqualTo(userService.signup(signupRequestDto).getUsername());
    }

    @DisplayName("회원가입 실패 - 중복된 유저네임")
    @Test
    void signup_fail1() {
        // given
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(username, password, UserRoleEnum.USER);

        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));

        // when & then
        assertThatThrownBy(() -> userService.signup(signupRequestDto)).isInstanceOf(
            IllegalArgumentException.class);
    }


    @DisplayName("회원가입 어드민 성공")
    @Test
    void adminSignup() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(username, password, UserRoleEnum.ADMIN);

        given(userRepository.findByUsername(username)).willReturn(Optional.empty());
        given(userRepository.save(any(User.class))).willReturn(user);
        userService.adminSignup(signupRequestDto);
        assertThat(username).isEqualTo(userService.signup(signupRequestDto).getUsername());

    }

    @DisplayName("회원가입 실패 - 중복된 유저네임")
    @Test
    void signup_fail2() {
        // given
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = new User(username, password, UserRoleEnum.USER);

        given(userRepository.findByUsername(username)).willReturn(Optional.of(user));

        // when & then
        assertThatThrownBy(() -> userService.signup(signupRequestDto)).isInstanceOf(
            IllegalArgumentException.class);
    }
}