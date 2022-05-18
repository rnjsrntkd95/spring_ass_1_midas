package com.epkorea.backoffice.dto;

import com.epkorea.backoffice.entity.User;

public class UserDto {
    private Integer id;
    private String userid;
    private String password;
    private String username;
    private String group;
    private String phone;

    public UserDto(User user) {
        this.id = user.getId();
        this.userid = user.getUserid();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.group = user.getGroup();
        this.phone = user.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
