package com.galaxy.memorit.common.authtemp.handler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
@RequiredArgsConstructor
public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    private final HandlerExceptionResolver handlerExceptionResolver;

//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response,
//        AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
//    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        org.springframework.security.access.AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
    }
}
