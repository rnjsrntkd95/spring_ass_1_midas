package com.epkorea.backoffice.repository.custom;

import com.epkorea.backoffice.entity.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserLogCustomRepository {
    Page<UserLog> findAllByUserId(String userid, Pageable pageable);
}
