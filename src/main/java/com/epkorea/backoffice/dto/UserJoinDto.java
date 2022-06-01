package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserJoinDto {
    @Getter
    @Setter
    public static class Request {
        private String userid;
        private String name;
        private String team;
        private String phone;
        private String pwd;
        private List<RoleEnum> roles;
    }
}
