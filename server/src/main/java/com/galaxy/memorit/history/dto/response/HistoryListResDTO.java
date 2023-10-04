package com.galaxy.memorit.history.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryListResDTO {
	private long totalPages;
	private long numOfFriends;
	private long numOfHistories;
	private List<HistoryListElementDTO> list;
}
