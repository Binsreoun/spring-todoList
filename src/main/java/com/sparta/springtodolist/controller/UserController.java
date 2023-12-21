package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.dto.restApi.RestApiResponseDto;
import com.sparta.springtodolist.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/users/signup")
    public ResponseEntity<RestApiResponseDto> signup(
        @Valid @RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new RestApiResponseDto("회원가입 성공", HttpStatus.CREATED.value(),
                userServiceImpl.signup(requestDto)));
    }


    @PostMapping("/users/admin/signup")
    public ResponseEntity<RestApiResponseDto> adminSignup(
        @Valid @RequestBody SignupRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new RestApiResponseDto("회원가입 성공", HttpStatus.CREATED.value(),
                userServiceImpl.adminSignup(requestDto)));
    }

}
