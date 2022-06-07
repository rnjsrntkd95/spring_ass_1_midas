package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class UserPageInfoRs {

    private List<UserPageInfoDto> userList;
    private int totalPages;
    private int currentPage;
    private Long totalElements;

    public static UserPageInfoRs toDto(Page<UserPageInfoDto> page, int currentPage) {
        UserPageInfoRs userPageInfoRs = new UserPageInfoRs();
        userPageInfoRs.setUserList(UserPageInfoDto.toDtoList(page.getContent()));
        userPageInfoRs.setTotalPages(page.getTotalPages());
        userPageInfoRs.setTotalElements(page.getTotalElements());
        userPageInfoRs.setCurrentPage(currentPage);

        return userPageInfoRs;
    }
}
