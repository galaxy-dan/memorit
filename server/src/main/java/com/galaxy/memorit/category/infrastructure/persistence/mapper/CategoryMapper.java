package com.galaxy.memorit.category.infrastructure.persistence.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.common.utils.converter.UUIDConverter;
@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper extends UUIDConverter {
}
