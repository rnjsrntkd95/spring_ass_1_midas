package com.epkorea.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserLogSearchDto {
    @Getter
    @Setter
    public static class Request {
        private String kwd;
        private int currentPage = 1;
    }
    @Getter
    @Builder
    @AllArgsConstructor
        public static class Response {
            private String userid;
            private String ip;
            private String sessionId;
            private boolean isLogin;
            private LocalDateTime loginDate;
        }
}
