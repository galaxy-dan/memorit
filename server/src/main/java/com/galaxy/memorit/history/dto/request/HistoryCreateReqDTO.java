package com.galaxy.memorit.history.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryCreateReqDTO {
	private String friendId;
	private LocalDate date;
	private String type;
	private Integer amount;
	private String item;
	private String detail;
	private String image;
	private boolean given;
}
