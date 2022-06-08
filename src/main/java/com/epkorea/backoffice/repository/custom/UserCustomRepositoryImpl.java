package com.epkorea.backoffice.repository.custom;

import com.epkorea.backoffice.dto.UserPageInfoDto;
import com.epkorea.backoffice.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.epkorea.backoffice.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<User> findByUseridWithRoles(String userid) {
        return Optional.ofNullable(queryFactory
                .select(user)
                .from(user)
                .innerJoin(user.roles)
                .fetchJoin()
                .where(user.userid.eq(userid))
                .fetchOne());
    }

    @Override
    public Page<UserPageInfoDto> findAllBySearchCondition(String condition, String kwd, Pageable pageable) {
        List<UserPageInfoDto> content = queryFactory
                .select(Projections.fields(
                        UserPageInfoDto.class,
                        user.userid,
                        user.name,
                        user.team,
                        user.createDate))
                .from(user)
                .where(allContains(condition, kwd), nameContains(condition, kwd), teamContains(condition, kwd))
                .orderBy(user.createDate.desc(), user.uid.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory
                .selectFrom(user)
                .where(allContains(condition, kwd), nameContains(condition, kwd), teamContains(condition, kwd))
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);
    }

    private BooleanExpression allContains(String condition, String kwd) {
        return condition != null && condition.equals("all") ? user.name.contains(kwd).or(user.team.contains(kwd)) : null;
    }

    private BooleanExpression nameContains(String condition, String kwd) {
        return condition != null && condition.equals("name") ? user.name.contains(kwd) : null;
    }

    private BooleanExpression teamContains(String condition, String kwd) {
        return condition != null && condition.equals("team") ? user.team.contains(kwd) : null;
    }
}
