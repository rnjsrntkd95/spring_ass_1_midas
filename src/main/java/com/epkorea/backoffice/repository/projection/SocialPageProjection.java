package com.epkorea.backoffice.repository.projection;

import java.time.LocalDateTime;

public interface SocialPageProjection {

    String getTitle();
    LocalDateTime getShowDate();
    boolean getIsShow();
}
