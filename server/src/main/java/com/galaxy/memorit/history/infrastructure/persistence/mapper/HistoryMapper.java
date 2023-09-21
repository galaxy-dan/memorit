package com.galaxy.memorit.history.infrastructure.persistence.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.common.utils.converter.UUIDConverter;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.history.domain.entity.History;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;

@Component
@Mapper(componentModel = "spring")
public interface HistoryMapper extends UUIDConverter {

	default HistoryResDTO entityToDTO(HistoryEntity entity){
		return HistoryResDTO.builder()
			//.username(entity.getUser().getNickName())
			.userName("싸피")
			.friendName(entity.getFriend().getName())
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
