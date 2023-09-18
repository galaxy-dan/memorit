package com.galaxy.memorit.category.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;
@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategoryEntity,Integer> {
	List<UserCategoryEntity> findAllByUserId(UUID userId);
}
