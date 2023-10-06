package com.galaxy.memorit.historytype.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import com.galaxy.memorit.historytype.dto.request.HistoryTypeSearchReqDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.galaxy.memorit.historytype.infrastructure.persistence.entity.QHistoryTypeEntity.historyTypeEntity;

@RequiredArgsConstructor
public class HistoryTypeDynamicQueryRepositoryImpl implements HistoryTypeDynamicQueryRepository{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<String> searchHistoryTypes(UUID userId, HistoryTypeSearchReqDTO dto) {
		String keyword = dto.getKeyword();

		JPAQuery<String> query = jpaQueryFactory.select(historyTypeEntity.typeName)
			.from(historyTypeEntity)
			.where(
				eqUserIdOrNull(userId),
				containsTypeName(keyword)
			);

		if(keyword != null){
			NumberExpression<Integer> sortOrder = new CaseBuilder()
				.when(startsWithTypeName(keyword)).then(1)
				.otherwise(0);
			query.orderBy(sortOrder.desc(),
				historyTypeEntity.typeName.asc());
		}else {
			query.orderBy(historyTypeEntity.typeName.asc());
		}

		return query.fetch();
	}

	public BooleanExpression eqUserIdOrNull(UUID userId){
		return historyTypeEntity.userId.eq(userId).or(historyTypeEntity.userId.isNull());
	}

	public BooleanExpression startsWithTypeName(String keyword){
		return keyword == null ? null : historyTypeEntity.typeName.startsWith(keyword);
	}

	public BooleanExpression containsTypeName(String keyword){
		return keyword == null ? null : historyTypeEntity.typeName.contains(keyword);
	}
}
