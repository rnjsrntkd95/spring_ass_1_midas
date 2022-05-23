package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.UserDto;
import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ModelAndView getAllUsers(@RequestParam @Nullable String condition, @RequestParam @Nullable String kwd) {
        List<UserDto.Response> users = userService.findAllUserInfo(condition, kwd);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("admin_list", users);
        modelAndView.setViewName("admin_list");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute UserLoginDto.Request userLoginDto, HttpServletRequest request) {
        UserLoginDto.Response user = userService.login(userLoginDto);
        if (user == null) {
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutProcess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:" + request.getHeader("Referer");
    }
}
