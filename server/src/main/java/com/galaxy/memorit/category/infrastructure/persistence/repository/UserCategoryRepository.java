package com.galaxy.memorit.category.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;

public interface UserCategoryRepository extends JpaRepository<UserCategoryEntity,Integer> {
	List<UserCategoryEntity> findAllByUserId(UUID userId);
}
