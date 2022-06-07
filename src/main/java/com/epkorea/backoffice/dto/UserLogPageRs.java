package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.UserLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class UserLogPageRs {

    private List<UserLogPageDto> userLogs;
    private int totalPages;
    private int currentPage;
    private Long totalElements;

    public static UserLogPageRs toDto(Page<UserLog> userLogPage, int currentPage) {
        UserLogPageRs rs = new UserLogPageRs();
        rs.setUserLogs(UserLogPageDto.toDtoList(userLogPage.getContent()));
        rs.setTotalPages(userLogPage.getTotalPages());
        rs.setTotalElements(userLogPage.getTotalElements());
        rs.setCurrentPage(currentPage);

        return rs;
    }

}
