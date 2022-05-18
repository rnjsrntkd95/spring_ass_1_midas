package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUseridAndPassword(String userid, String password);
}
