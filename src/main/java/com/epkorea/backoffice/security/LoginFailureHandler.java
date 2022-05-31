package com.epkorea.backoffice.security;

import com.epkorea.backoffice.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final UserLogService userLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        userLogService.loggingUserConnection(
                request.getParameter("username"),
                request.getRemoteAddr(),
                request.getRequestedSessionId(),
                false);

        response.sendRedirect("/user/login?error=NotFoundUserException");
    }
}
