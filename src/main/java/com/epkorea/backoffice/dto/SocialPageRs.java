package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class SocialPageRs {

    private List<SocialPageDto> socialList;
    private int currentPage;
    private int totalPages;
    private Long totalElements;

    public static SocialPageRs toDto(Page<SocialPageDto> socialListPageDto, int currentPage) {
        SocialPageRs rs = new SocialPageRs();
        rs.setSocialList(socialListPageDto.getContent());
        rs.setTotalPages(socialListPageDto.getTotalPages());
        rs.setTotalElements(socialListPageDto.getTotalElements());
        rs.setCurrentPage(currentPage);

        return rs;
    }
}
