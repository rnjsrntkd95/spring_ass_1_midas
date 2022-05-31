package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.SocialContribution;
import com.epkorea.backoffice.repository.projection.SocialPageProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<SocialContribution, Long> {
    Page<SocialPageProjection> findAllByOrderByShowDateDesc(Pageable pageable);
}
