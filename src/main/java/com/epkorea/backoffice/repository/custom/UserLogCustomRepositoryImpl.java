package com.epkorea.backoffice.repository.custom;

import com.epkorea.backoffice.entity.UserLog;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.epkorea.backoffice.entity.QUserLog.userLog;


@Repository
@RequiredArgsConstructor
public class UserLogCustomRepositoryImpl implements UserLogCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserLog> findAllByUserId(String userid, Pageable pageable) {
        List<UserLog> content = queryFactory
                .selectFrom(userLog)
                .where(useridContains(userid))
                .orderBy(userLog.loginDate.desc(), userLog.lid.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int totalSize = queryFactory
                .selectFrom(userLog)
                .where(useridContains(userid))
                .fetch().size();

        return new PageImpl<>(content, pageable, totalSize);

    }

    private BooleanExpression useridContains(String userid) {
        return userid != null ? userLog.userid.contains(userid) : null;
    }
}
