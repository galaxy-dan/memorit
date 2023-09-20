package com.galaxy.memorit.history.application.service;

import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;

public interface HistoryService {
	void createHistory(String userId, HistoryCreateReqDTO dto);
}
