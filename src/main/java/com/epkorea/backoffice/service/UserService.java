package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserJoinRq;
import com.epkorea.backoffice.dto.UserPageInfoDto;
import com.epkorea.backoffice.dto.UserPageInfoRq;
import com.epkorea.backoffice.dto.UserPageInfoRs;
import com.epkorea.backoffice.entity.Role;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private static final int PAGE_LENGTH = 10;
    private static final int PAGE_WEIGHT = 1;  // URL currentPage 파라매터 직관성을 위한 가중치

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUseridWithRoles(username)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Not Found User");
                });

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserid())
                .password(user.getPassword())
                .roles(user.getRoleNames())
                .build();
    }

    public UserPageInfoRs findAllUserInfo(UserPageInfoRq userPageInfoRq) {
        String condition = userPageInfoRq.getCondition();
        String kwd = userPageInfoRq.getKwd();
        Integer currentPage = userPageInfoRq.getCurrentPage();

        Page<UserPageInfoDto> page = userRepository.findAllBySearchCondition(condition, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));

        return UserPageInfoRs.toDto(page, currentPage - PAGE_WEIGHT);
    }

    @Transactional
    public Long joinUser(UserJoinRq userJoinRq) {
        validateDuplicateUser(userJoinRq.getUserid());
        User user = userJoinRq.toEntity(passwordEncoder);
        Role.createRoles(user, userJoinRq.getRoles());

        return userRepository.save(user).getUid();
    }

    public void validateDuplicateUser(String userid) {
        if (userRepository.findByUserid(userid).isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
