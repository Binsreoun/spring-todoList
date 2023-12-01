package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.BoardFinishRequestDto;
import com.sparta.springtodolist.dto.BoardRequestDto;
import com.sparta.springtodolist.dto.BoardResponseDto;
import com.sparta.springtodolist.dto.restApi.CommonResponseDto;
import com.sparta.springtodolist.dto.restApi.RestApiResponseDto;
import com.sparta.springtodolist.security.UserDetailsImpl;
import com.sparta.springtodolist.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/contents")
    private List<BoardResponseDto> getBoard(){
        return boardService.getBoard();
    }

    @GetMapping("/contents/{id}")
    private BoardResponseDto getBoardDetail(@PathVariable Long id){
        return boardService.getBoardDetail(id);
    }

    @PostMapping("/create")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.createBoard(boardRequestDto,userDetails.getUser());
    }

    @PostMapping("/update/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,@RequestBody BoardRequestDto boardRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.updateBoard(id,boardRequestDto,userDetails.getUser());
    }

    @PostMapping("/finish/{id}")
    public BoardResponseDto finishBoard(@PathVariable Long id, @RequestBody BoardFinishRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.finishBoard(id,boardRequestDto,userDetails.getUser());
    }
}
