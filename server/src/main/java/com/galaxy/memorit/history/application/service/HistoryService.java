package com.galaxy.memorit.history.application.service;

import java.util.List;

import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;

public interface HistoryService {
	void createHistory(String userId, HistoryCreateReqDTO dto);
	HistoryResDTO getHistory(String userId, long articleId);
	HistoryListResDTO getTotalHistory(String userId, String friendId);
}
