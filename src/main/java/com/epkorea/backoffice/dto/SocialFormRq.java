package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SocialFormRq {
    private Long sid;
    private String title;
    private String content;
    private String originFile;
    private String imageFilePath;
    private MultipartFile picture;
    private String isShow;
    private String showDate;
}
