package com.galaxy.memorit.category.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCategory {
	private String userId;
	private String categoryName;
}
