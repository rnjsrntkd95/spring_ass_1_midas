package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class UserDto {
    private Long id;
    private String userid;
    private String password;
    private String username;
    private String group;
    private String phone;
    private LocalDateTime createDate;

    public UserDto(User user) {
        this.id = user.getId();
        this.userid = user.getUserid();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.group = user.getGroup();
        this.phone = user.getPhone();
        this.createDate = user.getCreateDate();
    }
}
