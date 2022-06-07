package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.RoleEnum;
import com.epkorea.backoffice.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Getter
@Setter
public class UserJoinRq {

    private String userid;
    private String name;
    private String team;
    private String phone;
    private String pwd;
    private List<RoleEnum> roles;

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userid(userid)
                .name(name)
                .password(passwordEncoder.encode(pwd))
                .team(team)
                .phone(phone)
                .build();
    }
}
