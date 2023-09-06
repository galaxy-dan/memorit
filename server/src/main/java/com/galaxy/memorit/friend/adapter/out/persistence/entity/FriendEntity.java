package com.galaxy.memorit.friend.adapter.out.persistence.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendEntity {
	@Id
	@Column(name = "user_id", columnDefinition = "BINARY(16)")
	private UUID userId;
	@Id
	@Column(name = "friend_id", columnDefinition = "BINARY(16)")
	private UUID friendId;
	@Column(name = "name", length = 40, nullable = false)
	private String name;
	@Column(name = "category", length = 256, nullable = false)
	private String category;
	@Column(name = "received_count")
	private int receivedCount;
	@Column(name = "sent_count")
	private int sentCount;
	@Column(name = "received_money")
	private int receivedMoney;
	@Column(name = "sent_money")
	private int sentMoney;
}
