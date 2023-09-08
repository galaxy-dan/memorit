package com.galaxy.memorit.friend.domain;

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

	public String convertUUIDToString(byte[] byteArray){
		StringBuilder uuidString = new StringBuilder();
		for (byte b : byteArray) {
			uuidString.append(String.format("%02X", b));
		}
		return uuidString.toString();
	}
}
