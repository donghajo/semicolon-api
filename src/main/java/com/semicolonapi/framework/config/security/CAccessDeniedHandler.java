package com.semicolonapi.framework.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


import java.io.IOException;

public class CAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException {
        // 유저 정보는 있으나 자원에 접근할 수 있는 권한이 없는 경우 SC_FORBIDDEN (403)
        response.sendRedirect("/exception/accessdenied");
    }
}
