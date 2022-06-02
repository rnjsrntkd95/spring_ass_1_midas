package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.SocialContribution;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialFormResponseDto {
    private Long sid;
    private String title;
    private String content;
    private String originFile;
    private String imageFilePath;
    private String isShow;
    private LocalDate showDate;

    public static SocialFormResponseDto getSocialFrom(SocialContribution socialContribution) {
        return SocialFormResponseDto.builder()
                .sid(socialContribution.getSid())
                .title(socialContribution.getTitle())
                .content(socialContribution.getContent())
                .originFile(socialContribution.getOriginImagePath())
                .imageFilePath(socialContribution.getImagePath())
                .isShow(socialContribution.isShow() ? "Y" : "N")
                .showDate(socialContribution.getShowDate())
                .build();
    }
}
