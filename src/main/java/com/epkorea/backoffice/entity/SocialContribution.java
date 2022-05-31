package com.epkorea.backoffice.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="social_contributions")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Column(nullable = false)
    private String image;

    private boolean isShow;

    @Column(name = "show_date")
    private LocalDate showDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User writer;

    public static SocialContribution createSocialContribution(String image, boolean isShow, LocalDate showDate, User writer) {
        return SocialContribution.builder()
                .image(image)
                .isShow(isShow)
                .showDate(showDate)
                .writer(writer)
                .build();
    }
}
