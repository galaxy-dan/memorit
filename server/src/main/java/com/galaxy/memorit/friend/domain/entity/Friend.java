package com.galaxy.memorit.friend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Friend {
	private String userId;
	private String friendId;
	private String name;
	private String category;
	private int receivedCount;
	private int sentCount;
	private int receivedMoney;
	private int sentMoney;
}
