package com.galaxy.memorit.historytype.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.historytype.infrastructure.persistence.entity.UserHistoryTypeEntity;
@Repository
public interface UserHistoryTypeRepository extends JpaRepository<UserHistoryTypeEntity, Long> {
	List<UserHistoryTypeEntity> findAllByUserId(UUID userId);
}
