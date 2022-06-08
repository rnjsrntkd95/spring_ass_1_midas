package com.epkorea.backoffice.repository.custom;

import com.epkorea.backoffice.dto.UserPageInfoDto;
import com.epkorea.backoffice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserCustomRepository {

    Page<UserPageInfoDto> findAllBySearchCondition(String condition, String kwd, Pageable pageable);

    Optional<User> findByUseridWithRoles(@Param(value="userid") String userid);
}
