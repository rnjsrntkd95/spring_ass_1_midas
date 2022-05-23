package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.repository.mapper.UserMapper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserDto {
    @Getter
    @Setter
    public static class Request {
        private Integer currentPage = 0;
        private String condition;
        private String kwd;
    }

    @Getter
    public static class Response {
        private String userid;
        private String username;
        private String group;
        private LocalDateTime createDate;
        public Response(UserMapper user) {
            this.userid = user.getUserid();
            this.username = user.getUsername();
            this.group = user.getGroup();
            this.createDate = user.getCreateDate();
        }
    }
}
