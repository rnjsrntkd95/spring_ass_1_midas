package com.epkorea.backoffice.controller;

import com.epkorea.backoffice.entity.UserEntity;
import com.epkorea.backoffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = this.userService.findAllUserInfo();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
