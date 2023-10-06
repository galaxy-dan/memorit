package com.galaxy.memorit.historytype.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.historytype.infrastructure.persistence.entity.HistoryTypeEntity;

@Repository
public interface HistoryTypeRepository extends JpaRepository<HistoryTypeEntity, Long>, HistoryTypeDynamicQueryRepository {
}
