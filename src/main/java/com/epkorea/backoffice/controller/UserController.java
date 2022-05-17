package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUserInfo();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/login")
    public String login() {
        return "login_form";
    }

    @PostMapping("/user/login")
    public String loginProcess(@ModelAttribute User userParam, HttpServletRequest request) {
        User user = userService.login(userParam);
        if (user == null) {
            return "login_form";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return "redirect:" + request.getHeader("referer");
    }

    @GetMapping("/user/logout")
    public String loginProcess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:" + request.getHeader("referer");
    }
}
