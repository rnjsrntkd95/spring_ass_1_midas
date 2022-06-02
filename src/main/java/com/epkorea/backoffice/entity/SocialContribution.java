package com.epkorea.backoffice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "social_contributions")
@Getter
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
    private LocalDate showDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User writer;

    public static SocialContribution createSocialContribution(String title, String content, String originImagePath, String imagePath, boolean isShow, LocalDate showDate, User writer) {
        return SocialContribution.builder()
                .title(title)
                .content(content)
                .originImagePath(originImagePath)
                .imagePath(imagePath)
                .isShow(isShow)
                .showDate(showDate)
                .writer(writer)
                .build();
    }
}
