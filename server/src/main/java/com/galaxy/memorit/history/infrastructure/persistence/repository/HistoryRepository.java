package com.galaxy.memorit.history.infrastructure.persistence.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long>, HistoryDynamicQueryRepository{
	@Query("SELECT max(h.date) FROM HistoryEntity h WHERE h.userId = :userId AND h.friendId = :friendId")
	LocalDate findRecentDate(@Param("userId") UUID userId, @Param("friendId") UUID friendId);

	@Modifying
	@Query("DELETE FROM HistoryEntity h WHERE h.friendId = :friendId")
	void deleteAllByFriendId(@Param("friendId") UUID friendId);

	@Modifying
	@Query("DELETE FROM HistoryEntity h WHERE h.friendId IN :friendIds")
	void deleteAllByFriendIds(@Param("friendIds") List<UUID> friendIds);

}
