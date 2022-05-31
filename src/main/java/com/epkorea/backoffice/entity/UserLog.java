package com.epkorea.backoffice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_logs")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lid;

    private String userid;

    private String ip;

    private String sessionId;

    @Builder.Default
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
}
