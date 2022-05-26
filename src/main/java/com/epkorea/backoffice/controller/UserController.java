package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.UserJoinDto;
import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.dto.UsersPageInfoDto;
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

    @GetMapping("/all")
    public ModelAndView getAllUsers(@ModelAttribute UsersPageInfoDto.Request userDto) {
        UsersPageInfoDto.Response usersPageInfoDto = userService.findAllUserInfo(userDto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin_list");
        modelAndView.addObject("admin_list", usersPageInfoDto.getUserList());
        modelAndView.addObject("total_pages", usersPageInfoDto.getTotalPages());
        modelAndView.addObject("current_page", usersPageInfoDto.getCurrentPage());
        modelAndView.addObject("total_elements", usersPageInfoDto.getTotalElements());

        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute UserLoginDto.Request userLoginDto, HttpServletRequest request, HttpSession session) {
        userLoginDto.setSessionId(session.getId());
        userLoginDto.setIp(request.getRemoteAddr());

        UserLoginDto.Response user = userService.login(userLoginDto);
        if (user == null) {
            return "login";
        }
        session.setAttribute("user", user);

        return "redirect:/";
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
    public String signUpProcess(@ModelAttribute UserJoinDto.Request userJoinDto) {
        Long user_id =  userService.joinUser(userJoinDto);
        return "redirect:/user/all";
    }

    @GetMapping("/logs")
    public void getConnectionLogs(HttpServletRequest request) {

    }
}
