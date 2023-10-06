package com.galaxy.memorit.category.application.service;

import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;
import com.galaxy.memorit.category.dto.request.CategorySearchReqDTO;
import com.galaxy.memorit.category.dto.response.CategoryResDTO;

public interface CategoryService {
	void registerCategory(String userId, CategoryRegisterReqDTO dto);
	CategoryResDTO searchCategory(String userId, CategorySearchReqDTO dto);
}
