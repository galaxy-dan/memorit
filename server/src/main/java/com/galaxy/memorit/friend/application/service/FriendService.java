package com.galaxy.memorit.friend.application.service;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;

public interface FriendService {
	void registerFriend(byte[] uuid, FriendRegisterReqDTO dto);
}
