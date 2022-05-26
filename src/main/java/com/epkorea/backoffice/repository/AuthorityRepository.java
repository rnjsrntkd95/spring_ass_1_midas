package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
