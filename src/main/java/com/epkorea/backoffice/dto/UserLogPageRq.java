package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogPageRq {
    private String kwd;
    private int currentPage = 1;
}
