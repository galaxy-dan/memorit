package com.galaxy.memorit.friend.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRegisterFromAddressReqDTO {
	private List<String> nameList;
}
