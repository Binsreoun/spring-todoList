package com.sparta.springtodolist.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtodolist.MockSpringSecurityFilter;
import com.sparta.springtodolist.config.WebSecurityConfig;
import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.CommentRequestDto;
import com.sparta.springtodolist.dto.response.CommentResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.Comment;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import com.sparta.springtodolist.repository.BoardRepository;
import com.sparta.springtodolist.repository.CommentRepository;
import com.sparta.springtodolist.repository.UserRepository;
import com.sparta.springtodolist.security.UserDetailsImpl;
import com.sparta.springtodolist.service.BoardServiceImpl;
import com.sparta.springtodolist.service.CommentServiceImpl;
import com.sparta.springtodolist.service.UserServiceImpl;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(
    controllers = {CommentController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
class CommentControllerTest {

    @MockBean
    BoardServiceImpl boardServiceImpl;
    @MockBean
    BoardRepository boardRepository;
    @MockBean
    UserServiceImpl userServiceImpl;
    @MockBean
    UserRepository userRepository;
    @MockBean
    CommentServiceImpl commentServiceImpl;
    @MockBean
    CommentRepository commentRepository;

    private MockMvc mvc;
    private Principal mockPrincipal;

    private UserDetailsImpl userDetails;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .build();

    }

    private void mockUserSetup() {
        String username = "test123";
        String password = "qwer1234";
        User testUser = new User(username, password, UserRoleEnum.USER);
        testUser.setId(10L);
        userDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(userDetails, "",
            userDetails.getAuthorities());
    }

    @Test
    void viewComment() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();

        Comment comment = new Comment(commentRequestDto, userDetails.getUser(), board);
        comment.setId(1L);

        given(commentServiceImpl.viewComment(board.getId())).willReturn(
            (List.of(new CommentResponseDto(comment))));

        mvc.perform(get("/api/comments/contents/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void createComment() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();

        Comment comment = new Comment(commentRequestDto, userDetails.getUser(), board);
        comment.setId(1L);

        given(commentServiceImpl.createComment(board.getId(), commentRequestDto,
            userDetails.getUser())).willReturn(new CommentResponseDto(comment));

        String json = objectMapper.writeValueAsString(commentRequestDto);

        mvc.perform(post("/api/comments/create/1").accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .principal(mockPrincipal)
            )
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void updateComment() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();

        Comment comment = new Comment(commentRequestDto, userDetails.getUser(), board);
        comment.setId(1L);

        CommentRequestDto commentRequestDto2 = CommentRequestDto.builder()
            .detail("수정 댓글 디테일").build();

        String json = objectMapper.writeValueAsString(commentRequestDto2);

        given(commentServiceImpl.updateComment(board.getId(), commentRequestDto2,
            userDetails.getUser())).willReturn(new CommentResponseDto(comment));

        mvc.perform(post("/api/comments/update/1").accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .principal(mockPrincipal)
            )
            .andDo(print())
            .andExpect(status().isOk());

    }


    @Test
    void deleteComment() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);

        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
            .detail("테스트 댓글 디테일").build();
        Comment comment = new Comment(commentRequestDto, userDetails.getUser(), board);
        comment.setId(1L);

        given(commentServiceImpl.deleteComment(board.getId(), userDetails.getUser())).willReturn(
            1L);

        mvc.perform(post("/api/comments/delete/1").accept(MediaType.APPLICATION_JSON)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andDo(print())
            .andExpect(status().isOk());
    }

}