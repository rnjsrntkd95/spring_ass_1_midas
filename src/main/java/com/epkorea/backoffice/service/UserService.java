package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserDto;
import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto>  findAllUserInfo() {
        List<User> userList =  userRepository.findAll();
        return userList.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto login(UserLoginDto userLoginDto) {
        User user = userRepository.findByUseridAndPassword(userLoginDto.getUserid(), userLoginDto.getPassword());
        return new UserDto(user);
    }
}
