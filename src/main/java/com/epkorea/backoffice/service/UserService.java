package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserDto;
import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto.Response> findAllUserInfo(String condition, String kwd) {
        List<UserMapper> userList = new ArrayList<>();

        if (condition != null && kwd != null && !condition.isBlank() && !kwd.isBlank()) {
            if (condition.equals("userid")) {
                userList = userRepository.findAllByUseridLike(kwd);
            }
        } else {
            userList = userRepository.findAllBy();
        }
        return userList.stream().map(UserDto.Response::new).collect(Collectors.toList());
    }

    public UserLoginDto.Response login(UserLoginDto.Request userLoginDto) {
        User user = userRepository.findByUseridAndPassword(userLoginDto.getUserid(), userLoginDto.getPassword());
        return new UserLoginDto.Response(user);
    }
}
