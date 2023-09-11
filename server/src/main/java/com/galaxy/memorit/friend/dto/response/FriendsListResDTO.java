package com.galaxy.memorit.friend.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendsListResDTO {
	private List<FriendInfoDTO> list;
}
