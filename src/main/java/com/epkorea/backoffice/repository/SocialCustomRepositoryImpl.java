package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.dto.SocialListPageDto;
import com.epkorea.backoffice.entity.QSocialContribution;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SocialCustomRepositoryImpl implements SocialCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final QSocialContribution s = QSocialContribution.socialContribution;


    @Override
    public Page<SocialListPageDto> findAllBySearchCondition(String condition, String kwd, Pageable pageable) {
        List<SocialListPageDto> content =  queryFactory.select(Projections.bean(SocialListPageDto.class, s.title, s.showDate, s.isShow))
                .from(s)
                .where(titleContains(condition, kwd), contentContains(condition, kwd))
                .orderBy(s.showDate.desc(), s.sid.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory
                .selectFrom(s)
                .where(titleContains(condition, kwd), contentContains(condition, kwd))
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);



    }

    private BooleanExpression titleContains(String condition, String kwd) {
        return condition != null && condition.equals("title") ? s.title.contains(kwd) : null;
    }

    private BooleanExpression contentContains(String condition, String kwd) {
        return condition != null && condition.equals("content") ? s.content.contains(kwd) : null;
    }
}
