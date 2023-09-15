package com.galaxy.memorit.category.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.memorit.category.infrastructure.persistence.entity.UserCategoryEntity;

public interface UserCategoryRepository extends JpaRepository<UserCategoryEntity,Integer> {
}
