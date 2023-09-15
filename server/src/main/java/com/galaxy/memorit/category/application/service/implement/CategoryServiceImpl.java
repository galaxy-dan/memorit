package com.galaxy.memorit.category.application.service.implement;

import org.springframework.stereotype.Service;

import com.galaxy.memorit.category.application.service.CategoryService;
import com.galaxy.memorit.category.domain.entity.UserCategory;
import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;
import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;
import com.galaxy.memorit.category.infrastructure.persistence.mapper.CategoryMapper;
import com.galaxy.memorit.category.infrastructure.persistence.repository.UserCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final UserCategoryRepository userCategoryRepository;
	private final CategoryMapper categoryMapper;
	@Override
	public void registerCategory(String userId, CategoryRegisterReqDTO dto) {
		UserCategory userCategory = UserCategory.builder()
			.userId(userId)
			.categoryName(dto.getCategoryName())
			.build();
		System.out.println(userCategory.getUserId());
		System.out.println(userCategory.getCategoryName());
		userCategoryRepository.save(categoryMapper.toUserCategoryEntity(userCategory));
	}
}
