package com.galaxy.memorit.history.infrastructure.persistence.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.galaxy.memorit.common.entity.BaseEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "history")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEntity extends BaseEntity {
	@Id
	@Column(name = "article_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
	private UUID user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "friend_id", columnDefinition = "BINARY(16)", nullable = false)
	private FriendEntity friend;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "item")
	private String item;

	@Column(name = "detail")
	private String detail;

	@Column(name = "image")
	private String image;

	@Column(name = "is_given", nullable = false)
	private boolean given;
}
