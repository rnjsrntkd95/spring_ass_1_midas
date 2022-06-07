package com.epkorea.backoffice.repository.custom;

import com.epkorea.backoffice.dto.SocialListPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SocialCustomRepository {
    Page<SocialListPageDto> findAllBySearchCondition(String condition, String kwd, Pageable pageable);
}
