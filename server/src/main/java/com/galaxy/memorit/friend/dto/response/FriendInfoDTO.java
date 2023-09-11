package com.galaxy.memorit.friend.dto.response;

import com.galaxy.memorit.friend.domain.entity.Friend;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendInfoDTO {
	private String userId;
	private String friendId;
	private String name;
	private String category;
	private int receivedCount;
	private int sentCount;
	private int receivedMoney;
	private int sentMoney;

	public FriendInfoDTO(Friend friend) {
		this.userId = new String(friend.getUserId());
		this.friendId = new String(friend.getFriendId());
		this.name = friend.getName();
		this.category = friend.getCategory();
		this.receivedCount = friend.getReceivedCount();
		this.sentCount = friend.getSentCount();
		this.receivedMoney = friend.getReceivedMoney();
		this.sentMoney = friend.getSentMoney();
	}
}
