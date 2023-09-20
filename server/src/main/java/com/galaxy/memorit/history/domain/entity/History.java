package com.galaxy.memorit.history.domain.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class History {
	private Long id;
	private String userId;
	private String friendId;
	private LocalDate date;
	private String type;
	private Integer amount;
	private String item;
	private String detail;
	private String image;
	private boolean given;
}
