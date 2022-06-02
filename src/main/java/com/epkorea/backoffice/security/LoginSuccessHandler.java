package com.epkorea.backoffice.security;

import com.epkorea.backoffice.service.UserLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserLogService userLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        userLogService.loggingUserConnection(
                user.getUsername(),
                request.getRemoteAddr(),
                request.getRequestedSessionId(),
                true);

        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");

            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                response.sendRedirect(redirectUrl);
            } else {
                response.sendRedirect("/user/all");
            }
        } else {
            response.sendRedirect("/user/all");
        }
    }
}
