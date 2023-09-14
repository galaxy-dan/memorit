package com.galaxy.memorit.friend.Infrastructure.persistence.repository;


import java.util.List;
import java.util.UUID;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;

public interface FriendDynamicQueryRepository {
	List<FriendEntity> findFriendsByDTO(UUID userId, FriendSearchReqDTO dto);
}
