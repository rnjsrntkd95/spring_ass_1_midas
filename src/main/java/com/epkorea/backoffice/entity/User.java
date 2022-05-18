package com.epkorea.backoffice.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity(name="user")
@Table(name="users")
@Getter
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column
    private String group;

    @Column
    private String phone;
}
