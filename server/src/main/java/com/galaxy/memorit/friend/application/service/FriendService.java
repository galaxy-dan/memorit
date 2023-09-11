package com.galaxy.memorit.friend.application.service;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;

public interface FriendService {
	void registerFriend(String userId, FriendRegisterReqDTO dto);
	FriendsListResDTO getFriendsList(String userId);
}
