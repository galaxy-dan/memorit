package com.galaxy.memorit.category.application.service.implement;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.category.application.service.CategoryService;
import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;
import com.galaxy.memorit.category.dto.response.CategoryResDTO;
import com.galaxy.memorit.category.infrastructure.persistence.entity.BaseCategoryEntity;
import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;
import com.galaxy.memorit.category.infrastructure.persistence.mapper.CategoryMapper;
import com.galaxy.memorit.category.infrastructure.persistence.repository.BaseCategoryRepository;
import com.galaxy.memorit.category.infrastructure.persistence.repository.UserCategoryRepository;
import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final UserCategoryRepository userCategoryRepository;
	private final BaseCategoryRepository baseCategoryRepository;
	private final CategoryMapper categoryMapper;
	private final UserRepository userRepository;
	@Transactional
	@Override
	public void registerCategory(String userId, CategoryRegisterReqDTO dto) {
		UUID userUUID = categoryMapper.stringToUUID(userId);
		userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		UserCategoryEntity userCategoryEntity = UserCategoryEntity.builder()
			.userId(userUUID)
			.categoryName(dto.getCategoryName())
			.build();
		userCategoryRepository.save(userCategoryEntity);
	}

	@Transactional(readOnly = true)
	@Override
	public CategoryResDTO getCategory(String userId) {
		UUID userUUID = categoryMapper.stringToUUID(userId);
		userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		//base와 user 커스텀 카테고리 조회
		List<String> categoryList = baseCategoryRepository.findAll()
			.stream()
			.map(BaseCategoryEntity::getCategoryName)
			.collect(Collectors.toList());
		categoryList.addAll(userCategoryRepository.findAllByUserId(userUUID)
			.stream()
			.map(UserCategoryEntity::getCategoryName)
			.collect(Collectors.toList()));
		return new CategoryResDTO(categoryList);
	}
}
