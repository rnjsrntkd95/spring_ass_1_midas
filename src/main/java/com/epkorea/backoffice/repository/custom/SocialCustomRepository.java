package com.epkorea.backoffice.repository.custom;

import com.epkorea.backoffice.dto.SocialPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SocialCustomRepository {
    Page<SocialPageDto> findAllBySearchCondition(String condition, String kwd, Pageable pageable);
}
