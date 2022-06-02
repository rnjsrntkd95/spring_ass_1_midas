package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SocialFormRequestDto {
    private Long sid;
    private String title;
    private String content;
    private MultipartFile picture;
    private String isShow;
    private String showDate;
}
