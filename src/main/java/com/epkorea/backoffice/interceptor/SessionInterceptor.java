package com.epkorea.backoffice.interceptor;

import com.epkorea.backoffice.dto.UserLoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserLoginDto.Response user = (UserLoginDto.Response)session.getAttribute("user");

        if (user == null) {
            logger.info("Not Login User: " + request.getHeader("referer"));
            response.sendRedirect("/user/login");
            return false;
        }
        logger.info("Login User: " + user.getUsername());
        return true;
    }
}
