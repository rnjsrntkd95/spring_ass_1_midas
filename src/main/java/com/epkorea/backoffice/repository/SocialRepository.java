package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.SocialContribution;
import com.epkorea.backoffice.repository.custom.SocialCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialRepository extends JpaRepository<SocialContribution, Long>, SocialCustomRepository {

}
