package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.*;
import com.epkorea.backoffice.service.UserLogService;
import com.epkorea.backoffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserLogService userLogService;

    @GetMapping("/all")
    public ModelAndView getAllUsers(@ModelAttribute UserPageInfoRq userPageInfoRq) {
        UserPageInfoRs userPageInfoRs = userService.findAllUserInfo(userPageInfoRq);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_list");
        modelAndView.addObject("admin_list", userPageInfoRs.getUserList());
        modelAndView.addObject("total_pages", userPageInfoRs.getTotalPages());
        modelAndView.addObject("current_page", userPageInfoRs.getCurrentPage());
        modelAndView.addObject("total_elements", userPageInfoRs.getTotalElements());
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referer);
        return "login";
    }

    @GetMapping("/logout")
    public String logoutProcess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/signup")
    public String signUp() {
        return "admin_signup";
    }

    @PostMapping("/signup")
    public String signUpProcess(@ModelAttribute UserJoinRq userJoinRq) {
        userService.joinUser(userJoinRq);
        return "redirect:/user/all";
    }

    @GetMapping("/logs")
    public ModelAndView getConnectionLogs(@ModelAttribute UserLogSearchDto.Request userLogSearchDto) {
        UserLogPageDto userLogPageDto = userLogService.findUserLogs(userLogSearchDto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_log_list");
        modelAndView.addObject("admin_logs", userLogPageDto.getUserLogs());
        modelAndView.addObject("total_pages", userLogPageDto.getTotalPages());
        modelAndView.addObject("current_page", userLogPageDto.getCurrentPage());
        modelAndView.addObject("total_elements", userLogPageDto.getTotalElements());

        return modelAndView;
    }
}
