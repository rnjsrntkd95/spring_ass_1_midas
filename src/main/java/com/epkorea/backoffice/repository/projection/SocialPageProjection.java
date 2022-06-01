package com.epkorea.backoffice.repository.projection;

import java.time.LocalDate;

public interface SocialPageProjection {

    String getTitle();
    LocalDate getShowDate();
    boolean getIsShow();
}
