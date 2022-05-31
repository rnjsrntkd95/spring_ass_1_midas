package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.repository.mapper.SocialPageMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SocialResponseDto {

    private List<SocialPageMapper> socialList;
    private int currentPage;
    private int totalPages;
    private Long totalElements;

    public static SocialResponseDto createSocialResponse(int currentPage, Page<SocialPageMapper> socialPage) {
        final int PAGE_WEIGHT = 1;
        return SocialResponseDto.builder()
                .socialList(socialPage.getContent())
                .currentPage(currentPage - PAGE_WEIGHT)
                .totalPages(socialPage.getTotalPages())
                .totalElements(socialPage.getTotalElements())
                .build();
    }
}
