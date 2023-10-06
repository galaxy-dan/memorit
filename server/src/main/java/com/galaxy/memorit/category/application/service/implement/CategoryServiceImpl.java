package com.galaxy.memorit.category.application.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.category.application.service.CategoryService;
import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;
import com.galaxy.memorit.category.dto.request.CategorySearchReqDTO;
import com.galaxy.memorit.category.dto.response.CategoryResDTO;
import com.galaxy.memorit.category.infrastructure.persistence.entity.CategoryEntity;
import com.galaxy.memorit.category.infrastructure.persistence.mapper.CategoryMapper;
import com.galaxy.memorit.category.infrastructure.persistence.repository.CategoryRepository;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	private final UserRepository userRepository;
	@Transactional
	@Override
	public void registerCategory(String userId, CategoryRegisterReqDTO dto) {
		UUID userUUID = categoryMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		CategoryEntity categoryEntity = CategoryEntity.builder()
			.userId(userUUID)
			.categoryName(dto.getCategoryName())
			.build();
		categoryRepository.save(categoryEntity);
	}

	@Transactional(readOnly = true)
	@Override
	public CategoryResDTO searchCategory(String userId, CategorySearchReqDTO dto) {
		UUID userUUID = categoryMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		List<String> categoryList = categoryRepository.searchCategories(userUUID, dto);
		return new CategoryResDTO(categoryList);
	}
}
