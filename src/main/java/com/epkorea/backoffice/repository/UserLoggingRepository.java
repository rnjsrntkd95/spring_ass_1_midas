package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.UserLog;
import com.epkorea.backoffice.repository.custom.UserLogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoggingRepository extends JpaRepository<UserLog, Long>, UserLogCustomRepository {
}
