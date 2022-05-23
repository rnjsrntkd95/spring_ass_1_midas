package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.repository.mapper.UserMapper;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserDto {

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
