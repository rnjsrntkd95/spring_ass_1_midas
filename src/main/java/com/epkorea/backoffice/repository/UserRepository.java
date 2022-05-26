package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(String userid);
    User findByUseridAndPassword(String userid, String password);
    Page<UserMapper> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<UserMapper> findAllByUsernameContainingOrTeamContainingOrderByCreateDateDesc(String username, String team, Pageable pageable);
    Page<UserMapper> findAllByUsernameLikeOrderByCreateDateDesc(String username, Pageable pageable);
    Page<UserMapper> findAllByTeamLikeOrderByCreateDateDesc(String team, Pageable pageable);
}
