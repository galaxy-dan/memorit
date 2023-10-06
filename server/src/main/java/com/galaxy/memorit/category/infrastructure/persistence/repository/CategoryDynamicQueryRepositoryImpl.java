package com.galaxy.memorit.category.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import com.galaxy.memorit.category.dto.request.CategorySearchReqDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.galaxy.memorit.category.infrastructure.persistence.entity.QCategoryEntity.categoryEntity;

@RequiredArgsConstructor
public class CategoryDynamicQueryRepositoryImpl implements CategoryDynamicQueryRepository{
	private final JPAQueryFactory jpaQueryFactory;
	@Override
	public List<String> searchCategories(UUID userId, CategorySearchReqDTO dto) {
		String keyword = dto.getKeyword();

		JPAQuery<String> query = jpaQueryFactory.select(categoryEntity.categoryName)
			.from(categoryEntity)
			.where(
				eqUserIdOrNull(userId),
				containsCategoryName(keyword)
			);

		if(keyword != null){
			NumberExpression<Integer> sortOrder = new CaseBuilder()
				.when(startsWithCategoryName(keyword)).then(1)
				.otherwise(0);
			query.orderBy(sortOrder.desc(),
				categoryEntity.categoryName.asc());
		}else {
			query.orderBy(categoryEntity.categoryName.asc());
		}

		return query.fetch();
	}

	public BooleanExpression eqUserIdOrNull(UUID userId){
		return categoryEntity.userId.eq(userId).or(categoryEntity.userId.isNull());
	}

	public BooleanExpression startsWithCategoryName(String keyword){
		return keyword == null ? null : categoryEntity.categoryName.startsWith(keyword);
	}

	public BooleanExpression containsCategoryName(String keyword){
		return keyword == null ? null : categoryEntity.categoryName.contains(keyword);
	}
}
