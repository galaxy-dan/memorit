package com.galaxy.memorit.friend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRankResDTO {
	private FriendInfoDTO mostReceivedCount, mostSentCount, mostReceivedMoney, mostSentMoney;
}
