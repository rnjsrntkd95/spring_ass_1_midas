package com.epkorea.backoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class UserLoggingDto {
    @Getter
    @Builder
    @Setter
    @AllArgsConstructor
    public static class Request {
        private String userid;
        private String ip;
        private String sessionId;
        private boolean isLogin;
    }
    @Getter
    @Setter
    public static class Response {
        private String userid;

        private String ip;

        private String sessionId;

        private boolean isLogin;

        private LocalDateTime loginDate;
    }
}
