package com.galaxy.memorit.historytype.application.service;

import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;

public interface HistoryTypeService {
	void registerHistoryType(String userId, HistoryTypeRegisterReqDTO dto);
}
