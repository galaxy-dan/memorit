package com.galaxy.memorit.friend.Infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendKey;

public interface FriendRepository extends JpaRepository<FriendEntity, FriendKey> {

}
