package com.galaxy.memorit.friend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendSearchReqDTO {
	private String keyword;
	private String category;
	private Integer dataSize;
	private Integer pageNumber;
}
