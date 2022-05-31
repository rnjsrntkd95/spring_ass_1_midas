package com.epkorea.backoffice.repository.projection;

import java.time.LocalDateTime;


public interface UserMapper {
    String getUserid();
    String getName();
    String getTeam();
    LocalDateTime getCreateDate();
}
