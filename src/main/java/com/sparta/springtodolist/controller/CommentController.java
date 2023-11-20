package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.BoardRequestDto;
import com.sparta.springtodolist.dto.BoardResponseDto;
import com.sparta.springtodolist.dto.CommentRequestDto;
import com.sparta.springtodolist.dto.CommentResponseDto;
import com.sparta.springtodolist.security.UserDetailsImpl;
import com.sparta.springtodolist.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/contents/{id}")
    public List<CommentResponseDto> viewComment(@PathVariable Long id){
        return commentService.viewComment(id);
    }
    @PostMapping("/create/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id,commentRequestDto,userDetails.getUser());
    }

    @PostMapping("/update/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id,commentRequestDto,userDetails.getUser());
    }

    @PostMapping("/delete/{id}")
    public Long deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id,userDetails.getUser());
    }
}
