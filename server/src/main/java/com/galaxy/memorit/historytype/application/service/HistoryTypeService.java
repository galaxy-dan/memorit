package com.galaxy.memorit.historytype.application.service;

import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;
import com.galaxy.memorit.historytype.dto.response.HistoryTypeResDTO;

public interface HistoryTypeService {
	void registerHistoryType(String userId, HistoryTypeRegisterReqDTO dto);
	HistoryTypeResDTO getHistoryType(String userId);
}
