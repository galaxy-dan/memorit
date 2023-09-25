package com.galaxy.memorit.history.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
	List<HistoryEntity> findAllByUserIdOrderByDate(UUID userId);
	List<HistoryEntity> findAllByUserIdAndFriendIdOrderByDate(UUID userId, UUID friendId);
}
