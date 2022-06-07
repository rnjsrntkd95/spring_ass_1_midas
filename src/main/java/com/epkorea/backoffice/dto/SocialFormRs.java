package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.SocialContribution;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SocialFormRs {
    private Long sid;
    private String title;
    private String content;
    private String originFile;
    private String imageFilePath;
    private String isShow;
    private LocalDate showDate;

    public static SocialFormRs toDto(SocialContribution socialContribution) {
        SocialFormRs rs = new SocialFormRs();
        rs.setSid(socialContribution.getSid());
        rs.setTitle(socialContribution.getTitle());
        rs.setContent(socialContribution.getContent());
        rs.setOriginFile(socialContribution.getOriginImagePath());
        rs.setImageFilePath(socialContribution.getImagePath());
        rs.setIsShow(socialContribution.isShow() ? "Y" : "N");
        rs.setShowDate(socialContribution.getShowDate());

        return rs;
    }
}
