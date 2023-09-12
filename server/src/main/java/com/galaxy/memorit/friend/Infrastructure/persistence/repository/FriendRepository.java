package com.galaxy.memorit.friend.Infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

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
}
