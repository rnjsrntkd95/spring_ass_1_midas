package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.projection.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String userid);
    @Query("select u from User u join fetch u.authority where u.userid=:userid")
    Optional<User> findByUseridWithAuthorities(@Param(value="userid") String userid);
    Page<UserMapper> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<UserMapper> findAllByNameContainingOrTeamContainingOrderByCreateDateDesc(String name, String team, Pageable pageable);
    Page<UserMapper> findAllByNameContainingOrderByCreateDateDesc(String name, Pageable pageable);
    Page<UserMapper> findAllByTeamContainingOrderByCreateDateDesc(String team, Pageable pageable);
}
