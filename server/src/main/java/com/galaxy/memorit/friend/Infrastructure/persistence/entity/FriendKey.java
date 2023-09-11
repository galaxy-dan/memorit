package com.galaxy.memorit.friend.Infrastructure.persistence.entity;

import java.io.Serializable;
import java.util.UUID;

//FriendEntity의 복합키 표현을 위한 클래스
public class FriendKey implements Serializable {
	private UUID userId;
	private UUID friendId;
}
