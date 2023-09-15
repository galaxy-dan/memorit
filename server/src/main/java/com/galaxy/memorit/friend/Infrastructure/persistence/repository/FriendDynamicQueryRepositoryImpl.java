package com.galaxy.memorit.friend.Infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.galaxy.memorit.friend.Infrastructure.persistence.entity.QFriendEntity.friendEntity;

@RequiredArgsConstructor
public class FriendDynamicQueryRepositoryImpl implements FriendDynamicQueryRepository{
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<FriendEntity> findFriendsByDTO(UUID userId, FriendSearchReqDTO dto) {
		int dataSize = dto.getDataSize() == null ? 20 : dto.getDataSize();
		int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber()-1;
		int offset = dataSize * pageNumber;

		return jpaQueryFactory.selectFrom(friendEntity)
			.where(
				eqUserId(userId),
				containsName(dto.getKeyword()),
				eqCategory(dto.getCategory())
			)
			.offset(offset)
			.orderBy(friendEntity.name.asc())
			.limit(dataSize)
			.fetch();
	}

	public BooleanExpression eqUserId(UUID userId){
		return friendEntity.userId.eq(userId);
	}

	public BooleanExpression containsName(String keyword){
		return keyword == null ? null : friendEntity.name.contains(keyword);
	}

	public BooleanExpression eqCategory(String category){
		return category == null ? null : friendEntity.category.eq(category);
	}
}