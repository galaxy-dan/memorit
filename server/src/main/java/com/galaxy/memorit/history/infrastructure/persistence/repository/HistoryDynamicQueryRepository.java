package com.galaxy.memorit.history.infrastructure.persistence.repository;

import java.util.UUID;

public interface HistoryDynamicQueryRepository {
	Long getTotalPages(UUID userId, UUID friendId);
}
