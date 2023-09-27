package com.galaxy.memorit.history.infrastructure.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.galaxy.memorit.history.dto.request.HistoryListReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListElementDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;
import com.galaxy.memorit.history.infrastructure.persistence.mapper.HistoryMapper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.galaxy.memorit.history.infrastructure.persistence.entity.QHistoryEntity.historyEntity;

@RequiredArgsConstructor
public class HistoryDynamicQueryRepositoryImpl implements HistoryDynamicQueryRepository{
	private final JPAQueryFactory jpaQueryFactory;
	private final HistoryMapper historyMapper;

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

		Long totalCounts = jpaQueryFactory.select(historyEntity.count())
			.from(historyEntity)
			.where(whereConditions)
			.fetchOne();

		long totalPages = 1;
		List<HistoryListElementDTO> result = new ArrayList<>();

		if(totalCounts != null && totalCounts > 0){
			JPAQuery<HistoryEntity> query = jpaQueryFactory.selectFrom(historyEntity)
				.where(whereConditions)
				.orderBy(historyEntity.date.desc());

			int dataSize = dto.getDataSize() != null ? dto.getDataSize() : 20;

			int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber() - 1;
			int offset = dataSize * pageNumber;
			query.offset(offset).limit(dataSize);

			totalPages = (totalCounts + dataSize - 1) / dataSize;

			result = query.fetch().stream()
				.map(historyMapper::entityToListElementDTO)
				.collect(Collectors.toList());
		}
		return new HistoryListResDTO(totalPages, result);
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