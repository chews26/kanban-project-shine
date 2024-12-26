package com.example.prello.workspace.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WorkspaceRepositoryQueryImpl implements WorkspaceRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;
}
