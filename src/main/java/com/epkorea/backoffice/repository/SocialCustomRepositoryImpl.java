package com.epkorea.backoffice.repository;

import com.epkorea.backoffice.dto.SocialListPageDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.epkorea.backoffice.entity.QSocialContribution.socialContribution;

@Repository
@RequiredArgsConstructor
public class SocialCustomRepositoryImpl implements SocialCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SocialListPageDto> findAllBySearchCondition(String condition, String kwd, Pageable pageable) {
        List<SocialListPageDto> content = queryFactory
                .select(Projections.fields(
                        SocialListPageDto.class,
                        socialContribution.sid,
                        socialContribution.title,
                        socialContribution.showDate,
                        socialContribution.isShow))
                .from(socialContribution)
                .where(titleContains(condition, kwd), contentContains(condition, kwd))
                .orderBy(socialContribution.showDate.desc(), socialContribution.sid.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory
                .selectFrom(socialContribution)
                .where(titleContains(condition, kwd), contentContains(condition, kwd))
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    private BooleanExpression titleContains(String condition, String kwd) {
        return condition != null && condition.equals("title") ? socialContribution.title.contains(kwd) : null;
    }

    private BooleanExpression contentContains(String condition, String kwd) {
        return condition != null && condition.equals("content") ? socialContribution.content.contains(kwd) : null;
    }
}
