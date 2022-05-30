package com.epkorea.backoffice.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
  // URL currentPage 파라매터 직관성을 위한 가중치

@Getter
@Builder
public class UserLogPageDto {

    private List<UserLogSearchDto.Response> userLogs;
    private int totalPages;
    private int currentPage;
    private Long totalElements;

    public static UserLogPageDto setUserLogPageDto(UserLogSearchDto.Request userLogSearchDto, Page<UserLogSearchDto.Response> userLogPage) {
        final int PAGE_WEIGHT = 1;
        return UserLogPageDto.builder()
                .userLogs(userLogPage.getContent())
                .totalPages(userLogPage.getTotalPages())
                .totalElements(userLogPage.getTotalElements())
                .currentPage(userLogSearchDto.getCurrentPage() - PAGE_WEIGHT)
                .build();
    }

}
