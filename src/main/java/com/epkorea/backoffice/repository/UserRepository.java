package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String userid);
    User findByUseridAndPassword(String userid, String password);
    Page<UserMapper> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<UserMapper> findAllByNameContainingOrTeamContainingOrderByCreateDateDesc(String name, String team, Pageable pageable);
    Page<UserMapper> findAllByNameContainingOrderByCreateDateDesc(String name, Pageable pageable);
    Page<UserMapper> findAllByTeamContainingOrderByCreateDateDesc(String team, Pageable pageable);
}
