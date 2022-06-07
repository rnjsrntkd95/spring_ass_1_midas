package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoggingRepository extends JpaRepository<UserLog, Long> {
    Page<UserLog> findAllByOrderByLoginDateDesc(Pageable pageable);
    Page<UserLog> findAllByUseridContainingOrderByLoginDateDesc(String userid, Pageable pageable);
}
