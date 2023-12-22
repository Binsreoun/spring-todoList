package com.sparta.springtodolist.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springtodolist.common.exception.ErrorCode;
import com.sparta.springtodolist.common.exception.ServiceException;
import com.sparta.springtodolist.dto.response.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
@Slf4j(topic = "에러 핸들러")
@Generated
public class ExceptionHandleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ServiceException e) {
            setResponse(response);
            ErrorCode code = e.getCode();
            ErrorResponse<Object> errResponse = ErrorResponse.builder()
                    .code(code.getCode())
                    .message(code.getMessage())
                    .build();
            response.setStatus(code.getStatus().value());

            try {
                response.getWriter().write(new ObjectMapper().writeValueAsString(errResponse));
            } catch (IOException ignored) {
            }
        }
    }

    private void setResponse(HttpServletResponse response) {
        response.setStatus(response.getStatus()); // http status 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // JSON 설정
        response.setCharacterEncoding(StandardCharsets.UTF_8.name()); // UTF8 설정
    }
}