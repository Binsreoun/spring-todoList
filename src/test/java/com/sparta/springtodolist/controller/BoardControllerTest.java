package com.sparta.springtodolist.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtodolist.MockSpringSecurityFilter;
import com.sparta.springtodolist.config.WebSecurityConfig;
import com.sparta.springtodolist.dto.request.BoardRequestDto;
import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.dto.response.BoardResponseDto;
import com.sparta.springtodolist.entity.Board;
import com.sparta.springtodolist.entity.User;
import com.sparta.springtodolist.entity.UserRoleEnum;
import com.sparta.springtodolist.repository.BoardRepository;
import com.sparta.springtodolist.repository.UserRepository;
import com.sparta.springtodolist.security.UserDetailsImpl;
import com.sparta.springtodolist.service.BoardService;
import com.sparta.springtodolist.service.UserService;
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
    controllers = {BoardController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
class BoardControllerTest {

    @MockBean
    BoardService boardService;
    @MockBean
    BoardRepository boardRepository;
    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;

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
    void getBoard() throws Exception {
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test123")
            .password("12345678").build();
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        User user = new User(username, password, UserRoleEnum.USER);
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, user);
        given(boardService.getBoard()).willReturn(List.of(new BoardResponseDto(board)));

        mvc.perform(get("/api/boards/contents").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void getBoardDetail() throws Exception {
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

        given(boardService.getBoardDetail(board.getId())).willReturn(new BoardResponseDto(board));

        mvc.perform(get("/api/boards/contents/1").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void createBoard() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        String json = objectMapper.writeValueAsString(boardRequestDto);
        mvc.perform(post("/api/boards/create").accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void updateBoard() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);
        BoardRequestDto boardRequestDto2 = BoardRequestDto.builder().title("수정 타이틀")
            .detail("수정").build();
        Board board2 = new Board(boardRequestDto2, userDetails.getUser());

        given(boardService.updateBoard(board.getId(), boardRequestDto2,
            userDetails.getUser())).willReturn(new BoardResponseDto(board2));

        String json = objectMapper.writeValueAsString(boardRequestDto2);

        mvc.perform(put("/api/boards/update/1").accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void finishBoard() throws Exception {
        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);

        Board board2 = new Board(boardRequestDto, userDetails.getUser());
        board2.setFinish(true);
        board2.setId(1L);
        given(boardService.finishBoard(board.getId(), userDetails.getUser())).willReturn(
            new BoardResponseDto(board2));

        mvc.perform(patch("/api/boards/finish/1").accept(MediaType.APPLICATION_JSON)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void deleteBoard() throws Exception {
        this.mockUserSetup();

        this.mockUserSetup();
        BoardRequestDto boardRequestDto = BoardRequestDto.builder().title("테스트 타이틀")
            .detail("테스트 디테일").build();
        Board board = new Board(boardRequestDto, userDetails.getUser());
        board.setId(1L);

        given(boardService.deleteBoard(board.getId(), userDetails.getUser())).willReturn(
            "삭제 완료");

        mvc.perform(delete("/api/boards/delete/1").accept(MediaType.APPLICATION_JSON)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }
}