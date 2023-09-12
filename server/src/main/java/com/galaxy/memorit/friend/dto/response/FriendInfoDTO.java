package com.galaxy.memorit.friend.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendInfoDTO {
	private String userId;
	private String friendId;
	private String name;
	private String category;
	private int receivedCount;
	private int sentCount;
	private int receivedMoney;
	private int sentMoney;
	private LocalDate recentDate;
}
