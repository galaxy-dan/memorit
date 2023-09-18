package com.galaxy.memorit.category.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.memorit.category.infrastructure.persistence.entity.BaseCategoryEntity;

public interface BaseCategoryRepository extends JpaRepository<BaseCategoryEntity, Integer> {
}
