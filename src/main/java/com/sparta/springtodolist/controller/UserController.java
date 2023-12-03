package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.SignupRequestDto;
import com.sparta.springtodolist.dto.restApi.CommonResponseDto;
import com.sparta.springtodolist.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto,
        BindingResult bindingResult) {
        userService.userValidation(bindingResult);
        userService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }


    @PostMapping("/users/admin/signup")
    public ResponseEntity<CommonResponseDto> adminSignup(
        @Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        userService.userValidation(bindingResult);
        userService.adminSignup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }


}
