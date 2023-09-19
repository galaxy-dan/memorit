package com.galaxy.memorit.historytype.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.common.utils.converter.UUIDConverter;
import com.galaxy.memorit.historytype.domain.entity.UserHistoryType;
import com.galaxy.memorit.historytype.infrastructure.persistence.entity.UserHistoryTypeEntity;

@Component
@Mapper(componentModel = "spring")
public interface HistoryTypeMapper extends UUIDConverter {
	@Mapping(target = "userId", expression = "java(stringToUUID(userHistoryType.getUserId()))")
	UserHistoryTypeEntity toUserHistoryTypeEntity(UserHistoryType userHistoryType);
}
