package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserLogPageDto;
import com.epkorea.backoffice.dto.UserLogSearchDto;
import com.epkorea.backoffice.repository.UserLoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserLogService {
    private static final int PAGE_LENGTH = 10;
    private static final int PAGE_WEIGHT = 1;  // URL currentPage 파라매터 직관성을 위한 가중치

    @Autowired
    UserLoggingRepository userLoggingRepository;

    public UserLogPageDto findUserLogs(UserLogSearchDto.Request userLogSearchDto) {
        String kwd = userLogSearchDto.getKwd();
        Page<UserLogSearchDto.Response> userLogPage = null;
        if (kwd == null || kwd.isBlank()) {
            userLogPage =
                    userLoggingRepository.findAllByOrderByLoginDateDesc(PageRequest.of(userLogSearchDto.getCurrentPage() - PAGE_WEIGHT, PAGE_LENGTH));
        } else {
            userLogPage =
                    userLoggingRepository.findAllByUseridContainingOrderByLoginDateDesc(kwd, PageRequest.of(userLogSearchDto.getCurrentPage() - PAGE_WEIGHT, PAGE_LENGTH));
        }
        return UserLogPageDto.setUserLogPageDto(userLogSearchDto, userLogPage);
    }
}
