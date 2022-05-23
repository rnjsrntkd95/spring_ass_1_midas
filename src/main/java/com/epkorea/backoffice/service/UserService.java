package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserDto;
import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final int PAGE_LENGTH = 10;

    @Autowired
    private UserRepository userRepository;

    public List<UserDto.Response> findAllUserInfo(UserDto.Request userDto) {
        String condition = userDto.getCondition();
        String kwd = userDto.getKwd();
        Integer currentPage = userDto.getCurrentPage();
        List<UserMapper> userList = new ArrayList<>();

        if (condition != null && kwd != null && !condition.isBlank() && !kwd.isBlank()) {
            if (condition.equals("userid")) {
                userList = userRepository.findAllByUseridLikeOrderByCreateDateDesc(kwd, PageRequest.of(currentPage, PAGE_LENGTH));
            }
        } else {
            userList = userRepository.findAllByOrderByCreateDateDesc(PageRequest.of(currentPage, PAGE_LENGTH));
        }
        return userList.stream().map(UserDto.Response::new).collect(Collectors.toList());
    }

    public UserLoginDto.Response login(UserLoginDto.Request userLoginDto) {
        User user = userRepository.findByUseridAndPassword(userLoginDto.getUserid(), userLoginDto.getPassword());
        return new UserLoginDto.Response(user);
    }
}
