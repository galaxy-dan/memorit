package com.galaxy.memorit.friend.Infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendKey;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendKey> {
	List<FriendEntity> findAllByUserId(UUID userId);

	@Modifying
	@Query("DELETE FROM FriendEntity f WHERE f.userId = :userId AND f.friendId IN :friendIds")
	void deleteAllByFriendsList(@Param("userId") UUID userId, @Param("friendIds") List<UUID> friendIds);

	@Query("SELECT f FROM FriendEntity f WHERE f.userId = :userId AND f.receivedCount > 0 ORDER BY f.receivedCount DESC")
	List<FriendEntity> findFirstByReceivedCount(@Param("userId") UUID userId, Pageable pageable);
	@Query("SELECT f FROM FriendEntity f WHERE f.userId = :userId AND f.sentCount > 0 ORDER BY f.sentCount DESC")
	List<FriendEntity> findFirstBySentCount(@Param("userId") UUID userId, Pageable pageable);
	@Query("SELECT f FROM FriendEntity f WHERE f.userId = :userId AND f.receivedMoney > 0 ORDER BY f.receivedMoney DESC")
	List<FriendEntity> findFirstByReceivedMoney(@Param("userId") UUID userId, Pageable pageable);
	@Query("SELECT f FROM FriendEntity f WHERE f.userId = :userId AND f.sentMoney > 0 ORDER BY f.sentMoney DESC")
	List<FriendEntity> findFirstBySentMoney(@Param("userId") UUID userId, Pageable pageable);
}
