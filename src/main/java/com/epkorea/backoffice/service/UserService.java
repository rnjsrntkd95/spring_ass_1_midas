package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserJoinRq;
import com.epkorea.backoffice.dto.UsersPageInfoDto;
import com.epkorea.backoffice.entity.Role;
import com.epkorea.backoffice.entity.User;
import com.epkorea.backoffice.repository.UserRepository;
import com.epkorea.backoffice.repository.projection.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private static final int PAGE_LENGTH = 10;
    private static final int PAGE_WEIGHT = 1;  // URL currentPage 파라매터 직관성을 위한 가중치

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


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

    public UsersPageInfoDto.Response findAllUserInfo(UsersPageInfoDto.Request userDto) {
        String condition = userDto.getCondition();
        String kwd = userDto.getKwd();
        Integer currentPage = userDto.getCurrentPage();
        Page<UserMapper> page = null;
        if (condition != null && kwd != null && !condition.isBlank() && !kwd.isBlank()) {
            switch (condition) {
                case "all":
                    page = userRepository.findAllByNameContainingOrTeamContainingOrderByCreateDateDesc(kwd, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
                    break;
                case "name":
                    page = userRepository.findAllByNameContainingOrderByCreateDateDesc(kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));
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
