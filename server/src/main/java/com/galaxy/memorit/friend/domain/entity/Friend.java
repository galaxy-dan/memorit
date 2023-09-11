package com.galaxy.memorit.friend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Friend {
	private byte[] userId;
	private byte[] friendId;
	private String name;
	private String category;
	private int receivedCount;
	private int sentCount;
	private int receivedMoney;
	private int sentMoney;

	public String convertUserUUIDToString(){
		StringBuilder uuidString = new StringBuilder();
		for (byte b : this.userId) {
			uuidString.append(String.format("%02X", b));
		}
		return uuidString.toString();
	}
	public String convertFriendUUIDToString(){
		StringBuilder uuidString = new StringBuilder();
		for (byte b : this.friendId) {
			uuidString.append(String.format("%02X", b));
		}
		return uuidString.toString();
	}
}
