package com.epkorea.backoffice.repository.mapper;

import java.time.LocalDateTime;

public interface SocialPageMapper {

    String getTitle();
    LocalDateTime getShowDate();
    boolean getIsShow();
}
