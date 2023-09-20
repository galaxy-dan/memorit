package com.galaxy.memorit.history.infrastructure.persistence.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.common.utils.converter.UUIDConverter;
import com.galaxy.memorit.history.domain.entity.History;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;

@Component
@Mapper(componentModel = "spring", imports = UUID.class)
public interface HistoryMapper extends UUIDConverter {
	@Mapping(target = "userId", expression = "java(stringToUUID(history.getUserId()))")
	@Mapping(target = "friendId", expression = "java(stringToUUID(history.getFriendId()))")
	HistoryEntity toEntity(History history);
}
