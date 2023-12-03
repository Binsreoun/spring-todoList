package com.sparta.springtodolist.controller;

import com.sparta.springtodolist.dto.BoardRequestDto;
import com.sparta.springtodolist.dto.restApi.RestApiResponseDto;
import com.sparta.springtodolist.security.UserDetailsImpl;
import com.sparta.springtodolist.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/contents")
    private ResponseEntity<RestApiResponseDto> getBoard() {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                boardService.getBoard()));
    }

    @GetMapping("/contents/{id}")
    private ResponseEntity<RestApiResponseDto> getBoardDetail(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                boardService.getBoardDetail(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<RestApiResponseDto> createBoard(
        @RequestBody BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                boardService.createBoard(boardRequestDto, userDetails.getUser())));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestApiResponseDto> updateBoard(@PathVariable Long id,
        @RequestBody BoardRequestDto boardRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                boardService.updateBoard(id, boardRequestDto, userDetails.getUser())));
    }

    @PatchMapping("/finish/{id}")
    public ResponseEntity<RestApiResponseDto> finishBoard(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                boardService.finishBoard(id, userDetails.getUser())));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RestApiResponseDto> deleteBoard(@PathVariable Long id,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new RestApiResponseDto("성공", HttpStatus.OK.value(),
                boardService.deleteBoard(id, userDetails.getUser())));
    }
}
