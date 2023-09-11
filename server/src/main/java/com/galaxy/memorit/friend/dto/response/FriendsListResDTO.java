package com.galaxy.memorit.friend.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.galaxy.memorit.friend.domain.entity.Friend;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendsListResDTO {
	private List<FriendInfoDTO> list;

	public FriendsListResDTO(List<Friend> friendList) {
		this.list = friendList.stream()
			.map(FriendInfoDTO::new)
			.collect(Collectors.toList());
	}
}
