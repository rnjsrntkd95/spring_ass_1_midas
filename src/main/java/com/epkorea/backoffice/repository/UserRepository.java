package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUseridAndPassword(String userid, String password);
    List<UserMapper> findAllBy();
}
