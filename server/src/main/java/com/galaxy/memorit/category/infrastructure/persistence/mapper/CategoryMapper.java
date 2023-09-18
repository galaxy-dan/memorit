package com.galaxy.memorit.category.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.category.domain.entity.UserCategory;
import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;
import com.galaxy.memorit.common.utils.converter.UUIDConverter;
@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends UUIDConverter {
	@Mapping(target = "userId", expression = "java(stringToUUID(userCategory.getUserId()))")
	UserCategoryEntity toUserCategoryEntity(UserCategory userCategory);
}
