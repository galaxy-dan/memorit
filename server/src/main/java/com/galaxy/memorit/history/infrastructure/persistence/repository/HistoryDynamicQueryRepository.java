package com.galaxy.memorit.history.infrastructure.persistence.repository;

import java.util.UUID;

import com.galaxy.memorit.history.dto.request.HistoryListReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;

public interface HistoryDynamicQueryRepository {
	HistoryListResDTO getHistoriesByDTO(UUID userId, HistoryListReqDTO dto, UUID friendID);
}
