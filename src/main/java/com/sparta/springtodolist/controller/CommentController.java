package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.dto.restApi.RestApiResponseDto;
import com.sparta.springtodolist.security.UserDetailsImpl;
import com.sparta.springtodolist.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    @GetMapping("/contents/{id}")
    public ResponseEntity<RestApiResponseDto> viewComment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                commentServiceImpl.viewComment(id)));
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<RestApiResponseDto> createComment(@PathVariable Long id,
        @RequestBody CommentRequestDto commentRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                commentServiceImpl.createComment(id, commentRequestDto, userDetails.getUser())));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<RestApiResponseDto> updateComment(@PathVariable Long id,
        @RequestBody CommentRequestDto commentRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                commentServiceImpl.updateComment(id, commentRequestDto, userDetails.getUser())));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<RestApiResponseDto> deleteComment(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                commentServiceImpl.deleteComment(id, userDetails.getUser())));
    }
}
