package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserLoginDto;
import com.epkorea.backoffice.dto.UsersPageInfoDto;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final int PAGE_LENGTH = 10;
    private static final int PAGE_WEIGHT = 1;  // URL currentPage 파라매터 직관성을 위한 가중치

    @Autowired
    private UserRepository userRepository;

    public UsersPageInfoDto.Response findAllUserInfo(UsersPageInfoDto.Request userDto) {
        String condition = userDto.getCondition();
        String kwd = userDto.getKwd();
        Integer currentPage = userDto.getCurrentPage();
        Page<UserMapper> page = null;
        if (condition != null && kwd != null && !condition.isBlank() && !kwd.isBlank()) {
            switch (condition) {
                case "all":
                    page = userRepository.findAllByUsernameContainingOrGroupContainingOrderByCreateDateDesc(kwd, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
                case "username":
                    page = userRepository.findAllByUsernameLikeOrderByCreateDateDesc(kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
                case "group":
                    page = userRepository.findAllByGroupLikeOrderByCreateDateDesc(kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
            }
        } else {
            page = userRepository.findAllByOrderByCreateDateDesc(PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
        }

        UsersPageInfoDto.Response userPageInfoDto = UsersPageInfoDto.Response.builder()
                .userList(page.getContent())
                .totalPages(page.getTotalPages())
                .currentPage(userDto.getCurrentPage() - PAGE_WEIGHT)
                .totalElements(page.getTotalElements())
                .build();

        return userPageInfoDto;
    }

    public UserLoginDto.Response login(UserLoginDto.Request userLoginDto) {
        User user = userRepository.findByUseridAndPassword(userLoginDto.getUserid(), userLoginDto.getPassword());
        return new UserLoginDto.Response(user);
    }
}
