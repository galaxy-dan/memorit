package com.galaxy.memorit.category.infrastructure.persistence.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "baseCategory")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseCategoryEntity {
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "category_name", length = 128, nullable = false)
	private String categoryName;
}
