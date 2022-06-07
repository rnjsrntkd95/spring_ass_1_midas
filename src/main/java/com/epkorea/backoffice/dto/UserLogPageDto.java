package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.UserLog;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserLogPageDto {

    private String userid;
    private String ip;
    private String sessionId;
    private boolean isLogin;
    private LocalDateTime loginDate;

    public static UserLogPageDto toDto(UserLog log) {
        UserLogPageDto dto = new UserLogPageDto();
        dto.setUserid(log.getUserid());
        dto.setIp(log.getIp());
        dto.setSessionId(log.getSessionId());
        dto.setLogin(log.isLogin());
        dto.setLoginDate(log.getLoginDate());

        return dto;
    }

    public static List<UserLogPageDto> toDtoList(List<UserLog> userLogs) {
        return userLogs.stream().map(UserLogPageDto::toDto).collect(Collectors.toList());
    }
}
