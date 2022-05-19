package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserDto;
import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto>  findAllUserInfo() {
        List<User> userList = userRepository.findAll();

        return userList.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserLoginDto.Response login(UserLoginDto.Request userLoginDto) {
        User user = userRepository.findByUseridAndPassword(userLoginDto.getUserid(), userLoginDto.getPassword());
        return new UserLoginDto.Response(user);
    }
}
