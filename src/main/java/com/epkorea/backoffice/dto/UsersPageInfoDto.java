package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.repository.mapper.UserMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class UsersPageInfoDto {
    @Getter
    @Setter
    public static class UserDto {
        private String userid;
        private String username;
        private String team;
        private LocalDateTime createDate;

        public UserDto(UserMapper user) {
            this.userid = user.getUserid();
            this.username = user.getUsername();
            this.team = user.getTeam();
            this.createDate = user.getCreateDate();
        }
    }
    @Getter
    @Setter
    public static class Request {
        private Integer currentPage = 1;
        private String condition;
        private String kwd;
    }
    @Getter
    @Builder
    public static class Response {
        private List<UserMapper> userList;
        private int totalPages;
        private int currentPage;
        private Long totalElements;
    }
}
