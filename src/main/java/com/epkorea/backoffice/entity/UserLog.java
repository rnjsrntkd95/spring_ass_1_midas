package com.epkorea.backoffice.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

    private String userid;

    private String ip;

    private String sessionId;

    private boolean isLogin = false;

    @Column(name = "login_date")
    private LocalDateTime loginDate;

    public static UserLog createUserLog(String userid, String ip, String sessionId, boolean isLogin) {
        return UserLog.builder()
                .userid(userid)
                .ip(ip)
                .sessionId(sessionId)
                .isLogin(isLogin)
                .build();
    }

    public static UserLogBuilder builder() {
        return new UserLogBuilder();
    }

    public UserLog(Long lid, String userid, String ip, String sessionId, boolean isLogin, LocalDateTime loginDate) {
        this.lid = lid;
        this.userid = userid;
        this.ip = ip;
        this.sessionId = sessionId;
        this.isLogin = isLogin;
        this.loginDate = loginDate;
    }

    public static class UserLogBuilder {

        private Long lid;
        private String userid;
        private String ip;
        private String sessionId;
        private boolean isLogin;
        private LocalDateTime loginDate;

        public UserLogBuilder lid(Long lid) {
            this.lid = lid;
            return this;
        }

        public UserLogBuilder userid(String userid) {
            this.userid = userid;
            return this;
        }

        public UserLogBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public UserLogBuilder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public UserLogBuilder isLogin(boolean isLogin) {
            this.isLogin = isLogin;
            return this;
        }

        public UserLogBuilder loginDate(LocalDateTime loginDate) {
            this.loginDate = loginDate;
            return this;
        }

        public UserLog build() {
            return new UserLog(lid, userid, ip, sessionId, isLogin, loginDate);
        }
    }
}
