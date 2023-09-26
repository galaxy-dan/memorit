package com.galaxy.memorit.history.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryListReqDTO {
	private String friendId;
	private Integer dataSize;
	private Integer pageNumber;
	private Boolean given;
}
