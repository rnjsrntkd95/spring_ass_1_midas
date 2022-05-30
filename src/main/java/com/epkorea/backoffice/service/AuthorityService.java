package com.epkorea.backoffice.service;

import com.epkorea.backoffice.dto.AuthorityDto;
import com.epkorea.backoffice.entity.Authority;
import com.epkorea.backoffice.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public Authority grantAuthority(AuthorityDto authorityDto) {
        Authority authority = Authority.builder()
                .admin(authorityDto.isAdmin())
                .upsProduct(authorityDto.isUpsProduct())
                .coolingProduct(authorityDto.isCoolingProduct())
                .lightingProduct(authorityDto.isLightingProduct())
                .railroadProduct(authorityDto.isRailroadProduct())
                .upsExample(authorityDto.isUpsExample())
                .coolingExample(authorityDto.isCoolingExample())
                .lightingExample(authorityDto.isLightingExample())
                .railroadExample(authorityDto.isRailroadExample())
                .social(authorityDto.isSocial())
                .recruit(authorityDto.isRecruit())
                .build();

        return authorityRepository.save(authority);
    }
}
