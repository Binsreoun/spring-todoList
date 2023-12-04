package com.sparta.springtodolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.dto.response.BoardResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import com.sparta.springtodolist.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    BoardRepository boardRepository;

    BoardService boardService;

    @BeforeEach
    void setUp() {
        boardService = new BoardService(boardRepository);
    }

    @Test
    void getBoard() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();

        BoardRequestDto boardRequestDto2 = BoardRequestDto.builder().title("테스트 타이틀2")
            .detail("테스트 디테일2").build();

        Board board = new Board(boardRequestDto, user);
        Board board2 = new Board(boardRequestDto2, user);

        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        BoardResponseDto boardResponseDto2 = new BoardResponseDto(board2);

        given(boardRepository.findAll()).willReturn(
            List.of(board, board2));

        List<BoardResponseDto> list = boardService.getBoard();

        assertThat(list.get(0).getTitle()).isEqualTo(boardResponseDto.getTitle());
        assertThat(list.get(0).getDetail()).isEqualTo(boardResponseDto.getDetail());
        assertThat(list.get(1).getTitle()).isEqualTo(boardResponseDto2.getTitle());
        assertThat(list.get(1).getDetail()).isEqualTo(boardResponseDto2.getDetail());
    }

    @Test
    void getBoard_fail() {
        NullPointerException exception = assertThrows(NullPointerException.class,
            () -> boardService.getBoard());
        assertEquals("게시글이 없습니다.", exception.getMessage());
    }

    @Test
    void createBoard() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);

        given(boardRepository.save(any(Board.class))).willReturn(board);

        BoardResponseDto boardResponseDto = boardService.createBoard(boardRequestDto, user);

        assertThat(boardResponseDto.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardResponseDto.getDetail()).isEqualTo(board.getDetail());
        assertThat(boardResponseDto.getUsername()).isEqualTo(user.getUsername());
    }


    @Test
    void getBoardDetail() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));
        BoardResponseDto responseDto = boardService.getBoardDetail(board.getId());

        assertThat(responseDto.getUsername()).isEqualTo(board.getUser().getUsername());
        assertThat(responseDto.getTitle()).isEqualTo(board.getTitle());
        assertThat(responseDto.getDetail()).isEqualTo(board.getDetail());
    }

    @Test
    void getBoardDetail_fail() {
        NullPointerException exception = assertThrows(NullPointerException.class,
            () -> boardService.getBoardDetail(1L));
        assertEquals("게시글이 없습니다.", exception.getMessage());
    }


    @Test
    void updateBoard() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        BoardRequestDto boardRequestDto2 = BoardRequestDto.builder().title("수정 타이틀")
            .detail("수정 디테일").build();

        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));

        BoardResponseDto boardResponseDto = boardService.updateBoard(board.getId(),
            boardRequestDto2, user);

        assertThat(boardResponseDto.getUsername()).isEqualTo(board.getUser().getUsername());
        assertThat(boardResponseDto.getTitle()).isEqualTo(boardRequestDto2.getTitle());
        assertThat(boardResponseDto.getDetail()).isEqualTo(boardRequestDto2.getDetail());

    }

    @Test
    void updateBoard_fail1() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> boardService.updateBoard(board.getId(), boardRequestDto, user));
        assertEquals("선택한 게시물이 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    void updateBoard_fail2() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        SignupRequestDto signupRequestDto2 = SignupRequestDto.builder().username("test12")
            .password("12345678").build();
        String username2 = signupRequestDto2.getUsername();
        String password2 = signupRequestDto2.getPassword();
        User user2 = new User(username2, password2, UserRoleEnum.USER);
        user2.setId(2L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));

        BoardResponseDto boardResponseDto = boardService.updateBoard(board.getId(),
            boardRequestDto, user);

        BoardRequestDto boardRequestDto2 = BoardRequestDto.builder().title("수정 타이틀")
            .detail("수정 디테일").build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> boardService.updateBoard(1L, boardRequestDto2, user2));
        assertEquals("본인 이외에는 수정할수 없습니다.", exception.getMessage());
    }

    @Test
    void finishBoard() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);
        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));
        BoardResponseDto responseDto = boardService.finishBoard(board.getId(), user);
        assertThat(responseDto.isFinish());

    }

    @Test
    void finishBoard_fail1() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> boardService.finishBoard(board.getId(), user));
        assertEquals("선택한 게시물이 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    void finishBoard_fail2() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        SignupRequestDto signupRequestDto2 = SignupRequestDto.builder().username("test12")
            .password("12345678").build();
        String username2 = signupRequestDto2.getUsername();
        String password2 = signupRequestDto2.getPassword();
        User user2 = new User(username2, password2, UserRoleEnum.USER);
        user2.setId(2L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));

        BoardResponseDto boardResponseDto = boardService.updateBoard(board.getId(),
            boardRequestDto, user);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> boardService.finishBoard(board.getId(), user2));
        assertEquals("본인 이외에는 수정할수 없습니다.", exception.getMessage());
    }

    @Test
    void deleteBoard() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);
        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));
        String delete = boardService.deleteBoard(board.getId(), user);

        assertThat(delete).isEqualTo("삭제 완료");
    }

    @Test
    void deleteBoard_fail1() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> boardService.deleteBoard(board.getId(), user));
        assertEquals("선택한 게시물이 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    void deleteBoard_fail2() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        SignupRequestDto signupRequestDto2 = SignupRequestDto.builder().username("test12")
            .password("12345678").build();
        String username2 = signupRequestDto2.getUsername();
        String password2 = signupRequestDto2.getPassword();
        User user2 = new User(username2, password2, UserRoleEnum.USER);
        user2.setId(2L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        given(boardRepository.findById(board.getId())).willReturn(Optional.of(board));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> boardService.deleteBoard(board.getId(), user2));
        assertEquals("본인 이외에는 수정할수 없습니다.", exception.getMessage());
    }

}