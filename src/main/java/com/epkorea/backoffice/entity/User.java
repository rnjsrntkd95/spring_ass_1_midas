package com.epkorea.backoffice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="user")
@Table(name="users")
public class User {
    @Id
    private Integer id;

    private String userid;

    private String password;

    private String username;

    private String group;

    private String phone;

    public Integer getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getGroup() {
        return group;
    }

    public String getPhone() {
        return phone;
    }
}
