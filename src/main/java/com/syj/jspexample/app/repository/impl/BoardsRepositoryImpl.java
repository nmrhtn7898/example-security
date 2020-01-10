package com.syj.jspexample.app.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.syj.jspexample.app.entity.Boards;
import com.syj.jspexample.app.entity.QBoards;
import com.syj.jspexample.app.entity.QCategories;
import com.syj.jspexample.app.repository.custom.BoardsRepositoryCustom;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BoardsRepositoryImpl extends QuerydslRepositorySupport implements BoardsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardsRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Boards.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Boards> findBoardsPaging(int page, int pageSize, String category, String start, String end) {
        QBoards boards = QBoards.boards;
        return jpaQueryFactory
                .selectFrom(boards)
                .innerJoin(boards.categories).fetchJoin()
                .where(
                        boards.deleted.eq("N"),
                        boards.enabled.eq("Y"),
                        boards.categories.name.eq(category),
                        getDateBetween(start, end, boards)
                ).orderBy(
                        Expressions.stringTemplate("IFNULL({0}, {1})", boards.group, boards.id).desc(),
                        boards.seq.asc()
                ).offset(page * pageSize)
                .limit(pageSize)
                .fetch();
    }

    @Override
    public Long countBoards(String category, String start, String end) {
        QBoards boards = QBoards.boards;
        return jpaQueryFactory
                .select(boards.count())
                .from(boards)
                .where(
                        boards.deleted.eq("N"),
                        boards.enabled.eq("Y"),
                        boards.categories.name.eq(category),
                        getDateBetween(start, end, boards)
                ).fetchOne();
    }

    @Override
    public Long findGroupMaxSeq(Long id) {
        QBoards boards = QBoards.boards;
        return jpaQueryFactory
                .select(boards.seq.max())
                .from(boards)
                .where(
                        Expressions.stringTemplate("IFNULL({0}, {1})", boards.group, boards.id).eq(id + "")
                )
                .fetchOne() + 1L;
    }

    private BooleanExpression getDateBetween(String start, String end, QBoards boards) {
        return Expressions
                .stringTemplate("DATE_FORMAT({0}, '%Y-%m-%d')", boards.created)
                .between(start, end);
    }
}
