package com.sparta.springtodolist.util;


import com.sparta.springtodolist.common.util.JwtUtil;
import com.sparta.springtodolist.entity.UserRoleEnum;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class JwtUtilTest {

    @Autowired
    JwtUtil jwtUtil;

    @Mock
    private HttpServletRequest request;

    @Value("${jwt.secret.key}")
    private  String secretKey;             // Base64 Encode 한 SecretKey


    @BeforeEach
    void setUp() {
        jwtUtil.init();
    }

    @DisplayName("토큰 생성")
    @Test
    void createToken() {
        String username = "test123";
        // when
        String token = jwtUtil.createToken(username, UserRoleEnum.USER);

        // then
        assertNotNull(token);
    }

    @DisplayName("토큰 추출")
    @Test
    void resolveToken() {
        // given
        var token = "test-token";
        var bearerToken = "Bearer " + token;

        // when
        given(request.getHeader(JwtUtil.AUTHORIZATION_HEADER)).willReturn(bearerToken);
        var resolvedToken = jwtUtil.getJwtFromHeader(request);

        // then
        assertEquals(token, resolvedToken);
    }

    @DisplayName("토큰 추출 null")
    @Test
    void resolveToken2() {
        // given
        var token = "test-token";
        var bearerToken = token;

        // when
        given(request.getHeader(JwtUtil.AUTHORIZATION_HEADER)).willReturn(bearerToken);
        var resolvedToken = jwtUtil.getJwtFromHeader(request);

        // then
        assertEquals(null, resolvedToken);
    }

    @DisplayName("토큰에서 UserInfo 조회")
    @Test
    void getUserInfoFromToken() {
        // given
        String username = "test123";
        // when
        String token = jwtUtil.createToken(username, UserRoleEnum.USER).substring(7);

        // when
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        // then
        assertNotNull(claims);
        assertEquals(username, claims.getSubject());
    }

    @DisplayName("토큰 검증")
    @Nested
    class validateToken {

        @DisplayName("토큰 검증 성공")
        @Test
        void validateToken_success() {
            // given
            String username = "test123";
            String token = jwtUtil.createToken(username, UserRoleEnum.USER).substring(7);

            // when
            boolean isValid = jwtUtil.validateToken(token);

            // then
            assertTrue(isValid);
        }

    }

}