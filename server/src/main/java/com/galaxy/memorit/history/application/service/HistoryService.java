package com.galaxy.memorit.history.application.service;

import com.galaxy.memorit.history.dto.request.HistoryReqDTO;
import com.galaxy.memorit.history.dto.request.HistoryListReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;

public interface HistoryService {
	void createHistory(String userId, HistoryReqDTO dto);
	HistoryResDTO getHistory(String userId, long articleId);
	HistoryListResDTO getTotalHistory(String userId, HistoryListReqDTO dto);

	void updateHistory(String userId, Long articleId, HistoryReqDTO dto);

	void deleteHistory(String userId, Long articleId);
}
