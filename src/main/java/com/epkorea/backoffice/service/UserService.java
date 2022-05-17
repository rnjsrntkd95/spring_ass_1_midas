package com.epkorea.backoffice.service;

import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUserInfo() {
        return userRepository.findAll();
    }

    public User login(User user) {
        return userRepository.findByUseridAndPassword(user.getUserid(), user.getPassword());
    }
}
