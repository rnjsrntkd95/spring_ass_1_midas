package com.epkorea.backoffice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public String getAuthoritiesToString() {
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
