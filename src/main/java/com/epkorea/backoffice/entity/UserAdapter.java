package com.epkorea.backoffice.entity;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

@Getter
public class UserAdapter extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserAdapter(User user) {
        super(
                user.getUserid(),
                user.getPassword(),
                user.getRoles().stream()
                        .map((role -> "ROLE_" + role.getRole().name()))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        this.user = user;
    }
}
