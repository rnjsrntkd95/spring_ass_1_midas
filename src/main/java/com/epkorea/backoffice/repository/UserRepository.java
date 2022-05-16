package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
