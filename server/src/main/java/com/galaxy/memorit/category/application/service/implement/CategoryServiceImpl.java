package com.galaxy.memorit.category.application.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.galaxy.memorit.category.application.service.CategoryService;
import com.galaxy.memorit.category.domain.entity.UserCategory;
import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;
import com.galaxy.memorit.category.dto.response.CategoryResDTO;
import com.galaxy.memorit.category.infrastructure.persistence.entity.BaseCategoryEntity;
import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;
import com.galaxy.memorit.category.infrastructure.persistence.mapper.CategoryMapper;
import com.galaxy.memorit.category.infrastructure.persistence.repository.BaseCategoryRepository;
import com.galaxy.memorit.category.infrastructure.persistence.repository.UserCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final UserCategoryRepository userCategoryRepository;
	private final BaseCategoryRepository baseCategoryRepository;
	private final CategoryMapper categoryMapper;
	@Override
	public void registerCategory(String userId, CategoryRegisterReqDTO dto) {
		UserCategory userCategory = UserCategory.builder()
			.userId(userId)
			.categoryName(dto.getCategoryName())
			.build();
		userCategoryRepository.save(categoryMapper.toUserCategoryEntity(userCategory));
	}

	@Override
	public CategoryResDTO getCategory(String userId) {
		//base와 user 커스텀 카테고리 조회
		List<String> categoryList = baseCategoryRepository.findAll()
			.stream()
			.map(BaseCategoryEntity::getCategoryName)
			.collect(Collectors.toList());
		categoryList.addAll(userCategoryRepository.findAllByUserId(categoryMapper.stringToUUID(userId))
			.stream()
			.map(UserCategoryEntity::getCategoryName)
			.collect(Collectors.toList()));
		return new CategoryResDTO(categoryList);
	}
}
