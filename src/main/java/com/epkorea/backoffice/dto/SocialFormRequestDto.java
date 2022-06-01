package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class SocialFormRequestDto {
    private String title;
    private String content;
    private MultipartFile picture;
    private boolean isShow;
    private LocalDateTime showDate;
}
