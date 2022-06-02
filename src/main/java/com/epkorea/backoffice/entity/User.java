package com.epkorea.backoffice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "username")
    private String name;

    private String team;

    private String phone;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    List<Role> roles = new ArrayList<>();


    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public String[] getRoleNames() {
        return roles.stream().map(role -> role.getRole().name()).toArray(String[]::new);
    }

    public void addRoles(List<Role> roles) {
        this.roles.addAll(roles);
    }
}
