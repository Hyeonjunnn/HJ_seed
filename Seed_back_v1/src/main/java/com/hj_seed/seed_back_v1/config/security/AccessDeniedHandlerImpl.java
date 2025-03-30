package com.hj_seed.seed_back_v1.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj_seed.seed_back_v1.common.exception.dto.ApiErrorResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        // Jackson의 ObjectMapper를 사용하여 자바 객체를 JSON으로 변환 후 응답
        ObjectMapper mapper = new ObjectMapper();
        ApiErrorResponseDto errorResponseDto = new ApiErrorResponseDto(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.name(),
                HttpStatus.FORBIDDEN.getReasonPhrase()
        );

        log.info("[handle]");
        response.getWriter().write(
                mapper.writeValueAsString(errorResponseDto)
        );
    }
}