package com.galaxy.memorit.historytype.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import com.galaxy.memorit.historytype.dto.request.HistoryTypeSearchReqDTO;

public interface HistoryTypeDynamicQueryRepository {
	List<String> searchHistoryTypes(UUID userId, HistoryTypeSearchReqDTO dto);
}
