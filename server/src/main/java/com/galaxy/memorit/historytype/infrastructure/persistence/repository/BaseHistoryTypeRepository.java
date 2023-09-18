package com.galaxy.memorit.historytype.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.memorit.historytype.infrastructure.persistence.entity.BaseHistoryTypeEntity;

public interface BaseHistoryTypeRepository extends JpaRepository<BaseHistoryTypeEntity, Long> {
}
