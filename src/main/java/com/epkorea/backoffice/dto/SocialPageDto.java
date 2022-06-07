package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SocialPageDto {
    private Long sid;
    private String title;
    private LocalDate showDate;
    private boolean isShow;
}
