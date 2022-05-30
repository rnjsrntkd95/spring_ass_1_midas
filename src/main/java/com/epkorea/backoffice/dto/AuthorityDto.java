package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityDto {
    private final boolean admin;
    private final boolean upsProduct;
    private final boolean coolingProduct;
    private final boolean lightingProduct;
    private final boolean railroadProduct;
    private final boolean upsExample;
    private final boolean coolingExample;
    private final boolean lightingExample;
    private final boolean railroadExample;
    private final boolean social;
    private final boolean recruit;

    public AuthorityDto(Boolean admin, Boolean upsProduct, Boolean coolingProduct, Boolean lightingProduct, Boolean railroadProduct, Boolean upsExample, Boolean coolingExample, Boolean lightingExample, Boolean railroadExample, Boolean social, Boolean recruit) {
        this.admin = admin != null;
        this.upsProduct = upsProduct != null;
        this.coolingProduct = coolingProduct != null;
        this.lightingProduct = lightingProduct != null;
        this.railroadProduct = railroadProduct != null;
        this.upsExample = upsExample != null;
        this.coolingExample = coolingExample != null;
        this.lightingExample = lightingExample != null;
        this.railroadExample = railroadExample != null;
        this.social = social != null;
        this.recruit = recruit != null;
    }
}
