package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.SocialListPageDto;
import com.epkorea.backoffice.dto.SocialResponseDto;
import com.epkorea.backoffice.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialService {
    private final SocialRepository socialRepository;

    public SocialResponseDto getSocialList(int currentPage, String condition, String kwd) {
        int PAGE_WEIGHT = 1;
        int PAGE_LENGTH = 10;
        Page<SocialListPageDto> socialListPageDto = socialRepository.findAllBySearchCondition(condition, kwd, PageRequest.of(currentPage - PAGE_WEIGHT, PAGE_LENGTH));

        return SocialResponseDto.createSocialResponse(currentPage - PAGE_WEIGHT, socialListPageDto);
    }
}
