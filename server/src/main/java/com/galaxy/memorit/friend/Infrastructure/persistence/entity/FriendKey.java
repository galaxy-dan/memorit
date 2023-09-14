package com.galaxy.memorit.friend.Infrastructure.persistence.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//FriendEntity의 복합키 표현을 위한 클래스
@NoArgsConstructor
@AllArgsConstructor
public class FriendKey implements Serializable {
	private UUID userId;
	private UUID friendId;
}
