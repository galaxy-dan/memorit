package com.galaxy.memorit.friend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendUpdateReqDTO {
	private String name;
	private String category;
}
