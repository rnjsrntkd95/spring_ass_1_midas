package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.SocialContribution;
import com.epkorea.backoffice.repository.mapper.SocialPageMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<SocialContribution, Long> {
    Page<SocialPageMapper> findAllByOrderByShowDateDesc(Pageable pageable);
}
