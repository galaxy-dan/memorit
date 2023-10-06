package com.galaxy.memorit.friend.Infrastructure.persistence.repository;

import java.util.UUID;

import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;

public interface FriendDynamicQueryRepository {
	FriendsListResDTO findFriendsByDTO(UUID userId, FriendSearchReqDTO dto);
}
