package com.sparta.springtodolist.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardResponseDtoTest {

    private final User user = new User("username", "password", UserRoleEnum.USER);
    private final BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
        .detail("테스트 디테일").build();
    private final Board board = new Board(boardRequestDto, user);
    private final BoardResponseDto responseDto = new BoardResponseDto(board);


    @Test
    @DisplayName("BoardResponseDto 생성자 테스트")
    void BoardResponseDto() {
        assertNull(responseDto.getId());
        assertEquals("username", responseDto.getUsername());
        assertEquals("테스트 타이틀", responseDto.getTitle());
        assertEquals("테스트 디테일", responseDto.getDetail());
        assertFalse(responseDto.isFinish());
    }

    @Test
    void getId() {
        assertNull(responseDto.getId());
    }

    @Test
    void getUsername() {
        assertEquals("username", responseDto.getUsername());
    }

    @Test
    void getTitle() {
        assertEquals("테스트 타이틀", responseDto.getTitle());
    }

    @Test
    void getDetail() {
        assertEquals("테스트 디테일", responseDto.getDetail());
    }

    @Test
    void isFinish() {
        assertFalse(responseDto.isFinish());
    }

}