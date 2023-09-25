package com.galaxy.memorit.history.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.common.utils.converter.UUIDConverter;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;

@Component
@Mapper(componentModel = "spring")
public interface HistoryMapper extends UUIDConverter {

	default HistoryResDTO entityToDTO(HistoryEntity entity){
		return HistoryResDTO.builder()
			.articleId(String.valueOf(entity.getId()))
			.friendId(UUIDToHexString(entity.getFriendId()))
			.date(entity.getDate())
			.type(entity.getType())
			.amount(entity.getAmount())
			.item(entity.getItem())
			.detail(entity.getDetail())
			.image(entity.getImage())
			.given(entity.isGiven())
			.build();
	}
}
