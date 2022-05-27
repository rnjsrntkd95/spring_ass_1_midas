package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.*;
import com.epkorea.backoffice.entity.Authority;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.entity.UserLog;
import com.epkorea.backoffice.repository.UserLoggingRepository;
import com.epkorea.backoffice.repository.UserRepository;
import com.epkorea.backoffice.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserService {
    private static final int PAGE_LENGTH = 10;
    private static final int PAGE_WEIGHT = 1;  // URL currentPage 파라매터 직관성을 위한 가중치

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private UserLoggingRepository userLoggingRepository;

    public UsersPageInfoDto.Response findAllUserInfo(UsersPageInfoDto.Request userDto) {
        String condition = userDto.getCondition();
        String kwd = userDto.getKwd();
        Integer currentPage = userDto.getCurrentPage();
        Page<UserMapper> page = null;
        if (condition != null && kwd != null && !condition.isBlank() && !kwd.isBlank()) {
            switch (condition) {
                case "all":
                    page = userRepository.findAllByUsernameContainingOrTeamContainingOrderByCreateDateDesc(kwd, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
                case "username":
                    page = userRepository.findAllByUsernameContainingOrderByCreateDateDesc(kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
                case "team":
                    page = userRepository.findAllByTeamContainingOrderByCreateDateDesc(kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
            }
        } else {
            page = userRepository.findAllByOrderByCreateDateDesc(PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
        }

        return UsersPageInfoDto.Response.builder()
                .userList(page.getContent())
                .totalPages(page.getTotalPages())
                .currentPage(userDto.getCurrentPage() - PAGE_WEIGHT)
                .totalElements(page.getTotalElements())
                .build();
    }
    @Transactional
    public UserLoginDto.Response login(UserLoginDto.Request userLoginDto) {
        UserLoggingDto.Request.RequestBuilder logBuilder = UserLoggingDto.Request.builder()
                .userid(userLoginDto.getUserid())
                .ip(userLoginDto.getIp())
                .sessionId(userLoginDto.getSessionId());

        User user = userRepository.findByUseridAndPassword(userLoginDto.getUserid(), userLoginDto.getPassword());
        if (user == null) {
            loggingUserConnection(logBuilder.isLogin(false).build());
            return null;
        }
        loggingUserConnection(logBuilder.isLogin(true).build());

        return new UserLoginDto.Response(user);
    }
    @Transactional
    public Long joinUser(UserJoinDto.Request userJoinDto) {
        validateDuplicateUser(userJoinDto.getUserid());
        AuthorityDto authorityDto = userJoinDto.getAuthority();
        Authority authority = authorityService.grantAuthority(authorityDto);

        User user = User.builder()
                .userid(userJoinDto.getUserid())
                .username(userJoinDto.getUsername())
                .password(userJoinDto.getPwd())
                .team(userJoinDto.getTeam())
                .phone(userJoinDto.getPhone())
                .authority(authority)
                .build();
        user = userRepository.save(user);

        return user.getUid();
    }
    public void validateDuplicateUser(String userid) {
        User user = userRepository.findByUserid(userid);
        if (user != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    public void loggingUserConnection(UserLoggingDto.Request userLoggingDto) {
        UserLog userLog = UserLog.builder()
                .userid(userLoggingDto.getUserid())
                .ip(userLoggingDto.getIp())
                .sessionId(userLoggingDto.getSessionId())
                .isLogin(userLoggingDto.isLogin())
                .build();
        userLoggingRepository.save(userLog);
    }
}
