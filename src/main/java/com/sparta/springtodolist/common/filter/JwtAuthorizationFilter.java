package com.sparta.springtodolist.common.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtodolist.common.security.UserDetailsServiceImpl;
import com.sparta.springtodolist.common.util.JwtUtil;
import com.sparta.springtodolist.dto.restApi.RestApiExceptionDto;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
@Generated
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
        FilterChain filterChain) throws ServletException, IOException {

        String tokenValue = jwtUtil.getJwtFromHeader(req);

        if (StringUtils.hasText(tokenValue)) {

            if (jwtUtil.validateToken(tokenValue)) {
                Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
                try {
                    setAuthentication(info.getSubject());
                } catch (Exception e) {
                    RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(
                        "토큰이 유효하지 않습니다",
                        HttpStatus.BAD_REQUEST.value());
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    res.setContentType("application/json; charset=UTF-8");
                    res.getWriter().write(objectMapper.writeValueAsString(restApiExceptionDto));
                }
            } else {

                RestApiExceptionDto restApiExceptionDto = new RestApiExceptionDto(
                    "토큰이 유효하지 않습니다",
                    HttpStatus.BAD_REQUEST.value());
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                res.setContentType("application/json; charset=UTF-8");
                res.getWriter().write(objectMapper.writeValueAsString(restApiExceptionDto));
            }

        }
        filterChain.doFilter(req, res);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }


}