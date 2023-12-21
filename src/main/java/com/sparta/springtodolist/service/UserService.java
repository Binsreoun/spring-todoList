package com.sparta.springtodolist.service;

import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.entity.User;

public interface UserService {

    User signup(SignupRequestDto requestDto);

    User adminSignup(SignupRequestDto requestDto);

}
