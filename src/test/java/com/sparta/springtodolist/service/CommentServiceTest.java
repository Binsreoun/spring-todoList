package com.sparta.springtodolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.dto.response.CommentResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.Comment;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import com.sparta.springtodolist.repository.BoardRepository;
import com.sparta.springtodolist.repository.CommentRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    CommentRepository commentRepository;
    @Mock
    BoardRepository boardRepository;
    CommentService commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentService(commentRepository, boardRepository);
    }

    @Test
    void viewComment() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        given(boardRepository.findById(1L)).willReturn(Optional.of(board));
        given(commentRepository.findByBoard(board)).willReturn(List.of(comment));

        List<CommentResponseDto> list = commentService.viewComment(board.getId());

        assertThat(list.get(0).getDetail()).isEqualTo(commentRequestDto.getDetail());
    }

    @Test
    void viewComment_fail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> commentService.viewComment(3L));
        assertEquals("선택한 게시물이 존재하지 않습니다.", exception.getMessage());
    }


    @Test
    void createComment() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);
        given(boardRepository.findById(1L)).willReturn(Optional.of(board));
        given(commentRepository.save(any(Comment.class))).willReturn(comment);

        CommentResponseDto commentResponseDto = commentService.createComment(board.getId(),
            commentRequestDto, user);

        assertThat(commentResponseDto.getDetail()).isEqualTo(comment.getDetail());
    }

    @Test
    void createComment_fail() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> commentService.createComment(board.getId(),
                commentRequestDto, user));
        assertEquals("선택한 게시물이 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    void updateComment() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        CommentRequestDto commentRequestDto2 = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일2").build();

        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

        CommentResponseDto commentResponseDto = commentService.updateComment(comment.getId(),
            commentRequestDto2, user);

        assertThat(commentResponseDto.getDetail()).isEqualTo(commentRequestDto2.getDetail());
    }

    @Test
    void updateComment_fail1() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> commentService.updateComment(comment.getId(),
                commentRequestDto, user));
        assertEquals("선택한 댓글 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    void updateComment_fail2() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        SignupRequestDto signupRequestDto2 = SignupRequestDto.builder().username("test1233")
            .password("123456783").build();
        String username2 = signupRequestDto2.getUsername();
        String password2 = signupRequestDto2.getPassword();
        User user2 = new User(username2, password2, UserRoleEnum.USER);
        user.setId(2L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> commentService.updateComment(comment.getId(),
                commentRequestDto, user2));
        assertEquals("본인 이외에는 수정할수 없습니다.", exception.getMessage());
    }

    @Test
    void deleteComment() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);
        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));
        Long id = commentService.deleteComment(comment.getId(),
            user);
        assertThat(id).isEqualTo(comment.getId());
    }

    @Test
    void deleteComment_fail1() {
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

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> commentService.deleteComment(comment.getId(),
                user));
        assertEquals("선택한 댓글 존재하지 않습니다.", exception.getMessage());
    }

    @Test
    void deleteComment_fail2() {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        user.setId(1L);

        SignupRequestDto signupRequestDto2 = SignupRequestDto.builder().username("test1233")
            .password("123456783").build();
        String username2 = signupRequestDto2.getUsername();
        String password2 = signupRequestDto2.getPassword();
        User user2 = new User(username2, password2, UserRoleEnum.USER);
        user.setId(2L);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        board.setId(1L);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, user, board);
        comment.setId(1L);

        given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> commentService.deleteComment(comment.getId(),
                user2));
        assertEquals("본인 이외에는 수정할수 없습니다.", exception.getMessage());
    }

}