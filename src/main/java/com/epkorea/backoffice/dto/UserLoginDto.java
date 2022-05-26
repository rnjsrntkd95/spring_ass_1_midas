package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserLoginDto {
    @Getter @Setter
    public static class Request {
        private String userid;
        private String password;
    }
    @Getter
    public static class Response {
        private Long id;
        private String userid;
        private String username;
        private String team;
        private String phone;
        private LocalDateTime createDate;

        public Response(User user) {
            this.id = user.getUid();
            this.userid = user.getUserid();
            this.username = user.getUsername();
            this.team = user.getTeam();
            this.phone = user.getPhone();
            this.createDate = user.getCreateDate();
        }
    }
}
