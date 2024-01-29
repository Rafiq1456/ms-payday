package com.payment.system.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.system.dto.response.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, AuthenticationException ex)
            throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorResponse response = new ErrorResponse("Unauthorized");
        byte[] responseToSend = new ObjectMapper().writeValueAsString(response).getBytes(
                StandardCharsets.UTF_8);
        httpServletResponse.getOutputStream().write(responseToSend);
    }
}

