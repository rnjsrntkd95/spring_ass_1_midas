package com.epkorea.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserPageInfoDto {

    private String userid;
    private String name;
    private String team;
    private LocalDateTime createDate;

    public static UserPageInfoDto toDto(UserPageInfoDto user) {
        UserPageInfoDto userInfoDto = new UserPageInfoDto();
        userInfoDto.setUserid(user.getUserid());
        userInfoDto.setName(user.getName());
        userInfoDto.setTeam(user.getTeam());
        userInfoDto.setCreateDate(user.getCreateDate());

        return userInfoDto;
    }

    public static List<UserPageInfoDto> toDtoList(List<UserPageInfoDto> users) {
        return users.stream().map(UserPageInfoDto::toDto).collect(Collectors.toList());
    }
}
