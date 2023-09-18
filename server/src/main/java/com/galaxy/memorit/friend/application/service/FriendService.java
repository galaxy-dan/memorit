package com.galaxy.memorit.friend.application.service;
import com.galaxy.memorit.friend.dto.request.FriendMultiDeleteReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendRegisterFromAddressReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendUpdateReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;
import com.galaxy.memorit.friend.dto.response.FriendRankResDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;

public interface FriendService {
	void registerFriend(String userId, FriendRegisterReqDTO dto);
	void registerFriendsFromAddress(String userId, FriendRegisterFromAddressReqDTO dto);
	FriendInfoDTO getFriendInfo(String userId, String friendId);
	void updateFriendInfo(String userId, String friendId, FriendUpdateReqDTO dto);
	void deleteFriendById(String userId, String friendId);
	void deleteFriendsByList(String userId, FriendMultiDeleteReqDTO dto);
	FriendRankResDTO getFriendsRank(String userId);
	FriendsListResDTO searchFriends(String userId, FriendSearchReqDTO dto);
}
