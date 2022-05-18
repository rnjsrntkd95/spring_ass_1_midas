package com.epkorea.backoffice.interceptor;

import com.epkorea.backoffice.dto.UserLoginDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginStatusInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        UserLoginDto.Response user = (UserLoginDto.Response)request.getSession().getAttribute("user");
        if (user != null) {
            modelAndView.addObject("loginUser", user);
        }
    }
}
