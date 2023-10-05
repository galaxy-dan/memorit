package com.galaxy.memorit.history.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.galaxy.memorit.common.exception.AccessRefusedException;
import com.galaxy.memorit.common.exception.NoSuchFriendException;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.history.dto.request.HistoryListReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListElementDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;
import com.galaxy.memorit.history.infrastructure.persistence.mapper.HistoryMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.galaxy.memorit.history.infrastructure.persistence.entity.QHistoryEntity.historyEntity;

@RequiredArgsConstructor
public class HistoryDynamicQueryRepositoryImpl implements HistoryDynamicQueryRepository{
	private final JPAQueryFactory jpaQueryFactory;
	private final HistoryMapper historyMapper;
	private final FriendRepository friendRepository;

	@Override
	public HistoryListResDTO getHistoriesByDTO(UUID userId, HistoryListReqDTO dto, UUID friendId) {
		BooleanBuilder whereConditions = new BooleanBuilder().and(eqUserId(userId));
		if(friendId != null){
			whereConditions.and(eqFriendId(friendId));
		}
		Boolean given = dto.getGiven();
		if(given != null){
			whereConditions.and(eqGiven(given));
		}

		Tuple tuple = jpaQueryFactory.select(historyEntity.count().as("numOfHistories"),
				historyEntity.friendId.countDistinct().as("numOfFriends"))
			.from(historyEntity)
			.where(whereConditions)
			.fetchOne();

		long totalPages = 1;
		List<HistoryListElementDTO> result = new ArrayList<>();

		long numOfHistories = 0;
		long numOfFriends = 0;

		if(tuple != null){
			Long totalCounts = tuple.get(0, Long.class);
			if(totalCounts != null && totalCounts > 0) {
				JPAQuery<HistoryEntity> query = jpaQueryFactory.selectFrom(historyEntity)
					.where(whereConditions)
					.orderBy(historyEntity.date.desc());

				int dataSize = dto.getDataSize() != null ? dto.getDataSize() : 20;

				int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber() - 1;
				int offset = dataSize * pageNumber;
				query.offset(offset).limit(dataSize);

				totalPages = (totalCounts + dataSize - 1) / dataSize;

				result = query.fetch().stream()
					.map(entity -> {
						FriendEntity friendEntity = friendRepository.findById(entity.getFriendId()).orElseThrow(
							NoSuchFriendException::new);

						return historyMapper.entityToListElementDTO(entity, friendEntity.getName());
					})
					.collect(Collectors.toList());

				numOfHistories = totalCounts;
				numOfFriends = tuple.get(1, Long.class);
			}
		}
		return new HistoryListResDTO(totalPages, numOfFriends, numOfHistories, result);
	}
	public BooleanExpression eqUserId(UUID userId){
		return historyEntity.userId.eq(userId);
	}
	public BooleanExpression eqFriendId(UUID friendId){
		return historyEntity.friendId.eq(friendId);
	}
	public BooleanExpression eqGiven(Boolean given){
		return historyEntity.given.eq(given);
	}
}
