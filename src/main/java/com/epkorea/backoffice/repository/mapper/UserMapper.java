package com.epkorea.backoffice.repository.mapper;

import java.time.LocalDateTime;


public interface UserMapper {
    String getUserid();
    String getUsername();
    String getGroup();
    LocalDateTime getCreateDate();
}
