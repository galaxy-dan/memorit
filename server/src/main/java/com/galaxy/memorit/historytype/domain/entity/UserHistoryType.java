package com.galaxy.memorit.historytype.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserHistoryType {
	private String userId;
	private String typeName;
}
