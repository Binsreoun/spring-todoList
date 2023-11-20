package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.CommonResponseDto;
import com.sparta.springtodolist.dto.SignupRequestDto;
import com.sparta.springtodolist.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        try {
            // Validation 예외처리
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if (!fieldErrors.isEmpty()) {
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
                }
                throw new ValidationException("회원가입 오류입니다.");
            }
            userService.signup(requestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("중복된 유저이름 입니다.", HttpStatus.BAD_REQUEST.value()));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("회원가입 양식 오류입니다.", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/user/admin/signup")
    public ResponseEntity<CommonResponseDto> adminSignup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        try {
            // Validation 예외처리
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if (!fieldErrors.isEmpty()) {
                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
                }
                throw new ValidationException("회원가입 오류입니다.");
            }
            userService.adminSignup(requestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("중복된 유저이름 입니다.", HttpStatus.BAD_REQUEST.value()));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto("회원가입 양식 오류입니다.", HttpStatus.BAD_REQUEST.value()));
        }
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }


}
