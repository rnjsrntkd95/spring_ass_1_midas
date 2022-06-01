package com.epkorea.backoffice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "social_contributions")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Column(nullable = false)
    private String title;

    private String content;

    @Column(nullable = false)
    private String originImagePath;

    @Column(nullable = false)
    private String imagePath;

    @Builder.Default
    private boolean isShow = true;

    @Column(name = "show_date", nullable = false)
    private LocalDateTime showDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User writer;

    public static SocialContribution createSocialContribution(String originImagePath, String imagePath, boolean isShow, LocalDateTime showDate, User writer) {
        return SocialContribution.builder()
                .originImagePath(originImagePath)
                .imagePath(imagePath)
                .isShow(isShow)
                .showDate(showDate)
                .writer(writer)
                .build();
    }
}
