package com.galaxy.memorit.category.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.category.infrastructure.persistence.entity.BaseCategoryEntity;
@Repository
public interface BaseCategoryRepository extends JpaRepository<BaseCategoryEntity, Integer> {
}
