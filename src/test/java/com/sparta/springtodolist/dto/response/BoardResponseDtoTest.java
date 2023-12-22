package com.sparta.springtodolist.dto.response;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.LoginRequestDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardResponseDtoTest {

    private final User user = new User("username", "password", UserRoleEnum.USER);
    private final BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
        .detail("테스트 디테일").build();
    private final Board board = new Board(boardRequestDto, user);

    @Test
    @DisplayName("BoardResponseDto 생성자 테스트")
    void builder() {
        BoardResponseDto responseDto = new BoardResponseDto(board);
        assertNull(responseDto.getId());
        assertEquals("username", responseDto.getUsername());
        assertEquals("테스트 타이틀", responseDto.getTitle());
        assertEquals("테스트 디테일", responseDto.getDetail());
        assertFalse(responseDto.isFinish());
    }

    @Test
    void getId() {
        BoardResponseDto responseDto = new BoardResponseDto(board);
        assertNull(responseDto.getId());
    }

    @Test
    void getUsername() {
        BoardResponseDto responseDto = new BoardResponseDto(board);
        assertEquals("username", responseDto.getUsername());
    }

    @Test
    void getTitle() {
        BoardResponseDto responseDto = new BoardResponseDto(board);
        assertEquals("테스트 타이틀", responseDto.getTitle());
    }

    @Test
    void getDetail() {
        BoardResponseDto responseDto = new BoardResponseDto(board);
        assertEquals("테스트 디테일", responseDto.getDetail());
    }

    @Test
    void isFinish() {
        BoardResponseDto responseDto = new BoardResponseDto(board);
        assertFalse(responseDto.isFinish());
    }
    @Test
    void toStringTest() {
        String responseDto = BoardRequestDto.builder().toString();
        assertEquals(responseDto,"BoardRequestDto.BoardRequestDtoBuilder(title=null, detail=null)");                ;
    }
}