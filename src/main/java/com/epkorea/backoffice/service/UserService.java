package com.epkorea.backoffice.service;

import com.epkorea.backoffice.entity.UserEntity;
import com.epkorea.backoffice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAllUserInfo() {
        return userRepository.findAll();
    }
}
