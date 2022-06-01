package com.epkorea.backoffice.dto;

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

    public static SocialResponseDto createSocialResponse(int currentPage, Page<SocialListPageDto> socialListPageDto) {
        return SocialResponseDto.builder()
                .socialList(socialListPageDto.getContent())
                .currentPage(currentPage)
                .totalPages(socialListPageDto.getTotalPages())
                .totalElements(socialListPageDto.getTotalElements())
                .build();
    }
}
