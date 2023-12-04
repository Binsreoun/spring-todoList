package com.sparta.springtodolist.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.Comment;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserRoleEnum roleEnum = UserRoleEnum.USER;

    @Test
    void findByBoard() {
        String username = "username";
        String password = "123456789";
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, roleEnum);

        User user2 = userRepository.saveAndFlush(user);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();

        Board board = new Board(boardRequestDto, user2);

        Board board2 = boardRepository.saveAndFlush(board);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();

        Comment comment = new Comment(commentRequestDto, user2, board2);

        Comment comment2 = commentRepository.saveAndFlush(comment);

        List<Comment> list = commentRepository.findByBoard(board);

        assertThat(list.stream().findAny().get()).isEqualTo(comment);
    }

    @Test
    void findByBoard_fail() {
        String username = "username";
        String password = "123456789";
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, roleEnum);

        User User2 = userRepository.saveAndFlush(user);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();

        Board board = new Board(boardRequestDto, User2);

        Board board2 = boardRepository.saveAndFlush(board);

        List<Comment> list = commentRepository.findByBoard(board);

        assertThat(list.isEmpty()).isEqualTo(true);
    }

    @Test
    void findByBoard_fail02() {
        String username = "username";
        String password = "123456789";
        String username2 = "test";
        String password2 = "123456789t";
        String encodedPassword = passwordEncoder.encode(password);
        String encodedPassword2 = passwordEncoder.encode(password2);
        User user = new User(username, encodedPassword, roleEnum);

        User user2 = userRepository.saveAndFlush(user);

        User user3 = new User(username2, encodedPassword2, roleEnum);

        User user4 = userRepository.saveAndFlush(user3);

        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();

        Board board = new Board(boardRequestDto, user2);

        Board board2 = boardRepository.saveAndFlush(board);

        Board board3 = new Board(boardRequestDto, user4);

        Board board4 = boardRepository.saveAndFlush(board3);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();

        Comment comment = new Comment(commentRequestDto, user2, board2);

        Comment comment2 = commentRepository.saveAndFlush(comment);

        List<Comment> list = commentRepository.findByBoard(board3);

        assertThat(list.isEmpty()).isEqualTo(true);
    }

}