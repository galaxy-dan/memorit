package com.galaxy.memorit.history.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryListElementDTO {
	private String articleId;
	private String friendId;
	private LocalDate date;
	private String type;
	private Integer amount;
	private String item;
	private boolean given;
}
