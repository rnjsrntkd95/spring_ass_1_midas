package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

public class UserJoinDto {
    @Getter
    @Setter
    public static class Request {
        private String userid;
        private String username;
        private String team;
        private String phone;
        private String pwd;
        private AuthorityDto authority;

        public Request(String userid, String username, String team, String phone1, String phone2, String phone3, String pwd, Boolean admin, Boolean upsProduct, Boolean coolingProduct, Boolean lightingProduct, Boolean railroadProduct, Boolean upsExample, Boolean coolingExample, Boolean lightingExample, Boolean railroadExample, Boolean social, Boolean recruit) {
            this.userid = userid;
            this.username = username;
            this.team = team;
            this.phone = phone1 + '-' + phone2 + '-' + phone3;
            this.pwd = pwd;
            this.authority = new AuthorityDto(admin, upsProduct, coolingProduct, lightingProduct, railroadProduct, upsExample, coolingExample, lightingExample, railroadExample, social, recruit);
        }
    }
}
