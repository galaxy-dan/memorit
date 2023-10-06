package com.galaxy.memorit.category.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import com.galaxy.memorit.category.dto.request.CategorySearchReqDTO;

public interface CategoryDynamicQueryRepository {
	List<String> searchCategories(UUID userId, CategorySearchReqDTO dto);
}
