package com.galaxy.memorit.category.application.service;

import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;

public interface CategoryService {
	void registerCategory(String userId, CategoryRegisterReqDTO dto);
}
