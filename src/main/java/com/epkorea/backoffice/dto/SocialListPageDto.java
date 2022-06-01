package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.repository.projection.SocialPageProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialListPageDto {

    private String title;
    private LocalDate showDate;
    private boolean isShow;

    private static SocialListPageDto toDto(SocialPageProjection socialPageEntity) {
        return SocialListPageDto.builder()
                .title(socialPageEntity.getTitle())
                .showDate(socialPageEntity.getShowDate())
                .isShow(socialPageEntity.getIsShow())
                .build();
    }
    public static List<SocialListPageDto> toDtoList(List<SocialPageProjection> socialPageEntityList) {
        return socialPageEntityList.stream().map(SocialListPageDto::toDto).collect(Collectors.toList());
    }
}
