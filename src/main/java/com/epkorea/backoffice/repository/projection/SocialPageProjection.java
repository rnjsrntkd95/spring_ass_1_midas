package com.epkorea.backoffice.repository.projection;

import java.time.LocalDate;

public interface SocialPageProjection {

    Long getSid();
    String getTitle();
    LocalDate getShowDate();
    boolean getIsShow();
}
