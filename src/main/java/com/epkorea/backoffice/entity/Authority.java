package com.epkorea.backoffice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="authorities")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    private boolean admin;
    private boolean upsProduct;
    private boolean coolingProduct;
    private boolean lightingProduct;
    private boolean railroadProduct;
    private boolean upsExample;
    private boolean coolingExample;
    private boolean lightingExample;
    private boolean railroadExample;
    private boolean social;
    private boolean recruit;

    @OneToOne(mappedBy = "authority", fetch = FetchType.LAZY)
    private User user;

    public Set<String> getAuthoritiesSet() {
        Set<String> authorities = new HashSet<>();
        for(String col : this.toString().split(",")) {
            String[] authority = col.split("=");
            if (authority[1].equals("true")) {
                authorities.add(authority[0]);
            }
        }
        return authorities;
    }

    @Override
    public String toString() {
        return "admin=" + admin +
                ", upsProduct=" + upsProduct +
                ", coolingProduct=" + coolingProduct +
                ", lightingProduct=" + lightingProduct +
                ", railroadProduct=" + railroadProduct +
                ", upsExample=" + upsExample +
                ", coolingExample=" + coolingExample +
                ", lightingExample=" + lightingExample +
                ", railroadExample=" + railroadExample +
                ", social=" + social +
                ", recruit=" + recruit;
    }
}
