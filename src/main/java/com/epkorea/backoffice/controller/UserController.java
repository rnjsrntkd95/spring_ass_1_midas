package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.dto.UserDto;
import com.epkorea.backoffice.dto.UserLoginDto;
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
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUserInfo();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String loginProcess(@ModelAttribute UserLoginDto userLoginDto, HttpServletRequest request) {
        UserDto user = userService.login(userLoginDto);
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
