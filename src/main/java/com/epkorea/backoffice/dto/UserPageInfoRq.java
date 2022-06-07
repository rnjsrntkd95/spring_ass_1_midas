package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPageInfoRq {

        private Integer currentPage = 1;
        private String condition;
        private String kwd;
}
