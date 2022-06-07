package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.UserLogPageRq;
import com.epkorea.backoffice.dto.UserLogPageRs;
import com.epkorea.backoffice.entity.UserLog;
import com.epkorea.backoffice.repository.UserLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLogService {

    private static final int PAGE_LENGTH = 10;
    private static final int PAGE_WEIGHT = 1;  // URL currentPage 파라매터 직관성을 위한 가중치

    private final UserLogRepository userLogRepository;

    public UserLogPageRs findUserLogs(UserLogPageRq userLogPageRq) {
        String kwd = userLogPageRq.getKwd();
        Page<UserLog> userLogPage = userLogRepository.findAllByUserId(kwd, PageRequest.of(userLogPageRq.getCurrentPage() - PAGE_WEIGHT, PAGE_LENGTH));

        return UserLogPageRs.toDto(userLogPage, userLogPageRq.getCurrentPage() - PAGE_WEIGHT);
    }

    @Transactional
    public void loggingUserConnection(String userid, String ip, String sessionId, boolean isLogin) {
        UserLog userLog = UserLog.createUserLog(userid, ip, sessionId, isLogin);
        userLogRepository.save(userLog);
    }
}
