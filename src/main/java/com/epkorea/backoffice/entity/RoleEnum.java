package com.epkorea.backoffice.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum RoleEnum {
    ADMIN, SOCIAL, NEWS, RECRUIT,
    UPS_PRODUCT, COOLING_PRODUCT, LIGHTING_PRODUCT, RAILROAD_PRODUCT,
    UPS_EXAMPLE, COOLING_EXAMPLE, LIGHTING_EXAMPLE, RAILROAD_EXAMPLE
}
