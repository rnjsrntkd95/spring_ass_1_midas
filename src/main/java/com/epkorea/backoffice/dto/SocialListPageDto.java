package com.epkorea.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialListPageDto {
    private Long sid;
    private String title;
    private LocalDate showDate;
    private boolean isShow;
}
