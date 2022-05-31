package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.repository.projection.SocialPageProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SocialResponseDto {

    private List<SocialListPageDto> socialList;
    private int currentPage;
    private int totalPages;
    private Long totalElements;

    public static SocialResponseDto createSocialResponse(int currentPage, Page<SocialPageProjection> socialEntityList) {
        final int PAGE_WEIGHT = 1;

        return SocialResponseDto.builder()
                .socialList(SocialListPageDto.toDtoList(socialEntityList.getContent()))
                .currentPage(currentPage - PAGE_WEIGHT)
                .totalPages(socialEntityList.getTotalPages())
                .totalElements(socialEntityList.getTotalElements())
                .build();
    }
}
