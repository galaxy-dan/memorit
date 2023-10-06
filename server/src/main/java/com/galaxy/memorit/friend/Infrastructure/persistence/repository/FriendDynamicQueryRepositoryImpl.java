package com.galaxy.memorit.friend.Infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.mapper.FriendMapper;
import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.galaxy.memorit.friend.Infrastructure.persistence.entity.QFriendEntity.friendEntity;

@RequiredArgsConstructor
public class FriendDynamicQueryRepositoryImpl implements FriendDynamicQueryRepository{
	private final JPAQueryFactory jpaQueryFactory;
	private final FriendMapper friendMapper;

	@Override
	public FriendsListResDTO findFriendsByDTO(UUID userId, FriendSearchReqDTO dto) {
		String keyword = dto.getKeyword();
		Predicate eqUserId = eqUserId(userId);
		Predicate containsName = containsName(keyword);
		Predicate eqCategory = eqCategory(dto.getCategory());

		Long totalCounts = jpaQueryFactory.select(friendEntity.count())
			.from(friendEntity)
			.where(
				eqUserId,
				containsName,
				eqCategory
			).fetchOne();

		long totalPages = 1;
		List<FriendInfoDTO> result = new ArrayList<>();

		//결과 튜플 없으면 추가 쿼리 호출 없이 null 반환
		//있으면 조건에 맞는 튜플 반환
		if(totalCounts != null && totalCounts > 0){
			JPAQuery<FriendEntity> query = jpaQueryFactory.selectFrom(friendEntity)
				.where(
					eqUserId,
					containsName,
					eqCategory
				);

			if (keyword != null) {
				NumberExpression<Integer> sortOrder = new CaseBuilder()
					.when(startsWithName(keyword)).then(1)
					.otherwise(0);
				query.orderBy(sortOrder.desc(),
					orderSpecifier(dto.getSortBy()));
			} else {
				query.orderBy(orderSpecifier(dto.getSortBy()));
			}

			Integer dataSize = dto.getDataSize();
			if (dataSize != null) {
				int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber() - 1;
				int offset = dataSize * pageNumber;
				query.offset(offset).limit(dataSize);

				totalPages = (totalCounts + dataSize - 1) / dataSize;
			}

			result = query.fetch().stream()
				.map(friendMapper::toInfoDTO)
				.collect(Collectors.toList());
		}

		return new FriendsListResDTO(totalPages, result);
	}

	public BooleanExpression eqUserId(UUID userId){
		return friendEntity.userId.eq(userId);
	}

	public BooleanExpression startsWithName(String keyword){
		return keyword == null ? null : friendEntity.name.startsWith(keyword);
	}

	public BooleanExpression containsName(String keyword){
		return keyword == null ? null : friendEntity.name.contains(keyword);
	}

	public BooleanExpression eqCategory(String category){
		return category == null ? null : friendEntity.category.eq(category);
	}

	public OrderSpecifier<?> orderSpecifier(String sortBy){
		if(sortBy == null){
			sortBy = "name";
		}
		switch (sortBy){
			case "recentRegister":
				return friendEntity.createdAt.desc();
			case "lastRegister":
				return friendEntity.createdAt.asc();
			case "receivedCount":
				return friendEntity.receivedCount.desc();
			case "sentCount":
				return friendEntity.sentCount.desc();
			case "receivedMoney":
				return friendEntity.receivedMoney.desc();
			case "sentMoney":
				return friendEntity.sentMoney.desc();
			default:
				return friendEntity.name.asc();
		}
	}
}
