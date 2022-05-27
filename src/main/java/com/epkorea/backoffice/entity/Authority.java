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
    private boolean admin = false;
    private boolean upsProduct = false;
    private boolean coolingProduct = false;
    private boolean lightingProduct = false;
    private boolean railroadProduct = false;
    private boolean upsExample = false;
    private boolean coolingExample = false;
    private boolean lightingExample = false;
    private boolean railroadExample = false;
    private boolean social = false;
    private boolean recruit = false;

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
