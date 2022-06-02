package com.epkorea.backoffice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Table(name = "roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private User user;

    public static List<Role> createRoles(User user, List<RoleEnum> roles) {
        return roles.stream().map((role) -> Role.builder()
                .role(role)
                .user(user)
                .build()).collect(Collectors.toList());
    }
}
