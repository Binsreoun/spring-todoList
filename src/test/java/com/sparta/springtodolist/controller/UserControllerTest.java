package com.sparta.springtodolist.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtodolist.MockSpringSecurityFilter;
import com.sparta.springtodolist.config.WebSecurityConfig;
import com.sparta.springtodolist.dto.request.SignupRequestDto;
import com.sparta.springtodolist.repository.UserRepository;
import com.sparta.springtodolist.service.UserService;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(
    controllers = {UserController.class},
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
class UserControllerTest {

    @MockBean
    UserService userService;
    @MockBean
    UserRepository userRepository;
    private MockMvc mvc;
    private Principal mockPrincipal;

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

    @Test
    void signup() throws Exception {
        // given
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test456")
            .password("12345678").build();

        String jsonString = objectMapper.writeValueAsString(signupRequestDto);

        // when - then
        mvc.perform(post("/api/users/signup")
                .content(jsonString)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isCreated())
            .andDo(print());
    }

    @Test
    void adminSignup() throws Exception {
        // given
        SignupRequestDto signupRequestDto = SignupRequestDto.builder().username("test456")
            .password("12345678").build();

        String jsonString = objectMapper.writeValueAsString(signupRequestDto);

        // when - then
        mvc.perform(post("/api/users/signup")
                .content(jsonString)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isCreated())
            .andDo(print());
    }
}