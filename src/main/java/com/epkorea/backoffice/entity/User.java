package com.epkorea.backoffice.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="user")
@Table(name="users")
@Getter
public class User {
    @Id
    private Integer id;
    private String userid;
    private String password;
    private String username;
    private String group;
    private String phone;

}
