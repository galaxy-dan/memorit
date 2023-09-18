package com.galaxy.memorit.historytype.application.service.implement;

import org.springframework.stereotype.Service;

import com.galaxy.memorit.historytype.application.service.HistoryTypeService;
import com.galaxy.memorit.historytype.domain.entity.UserHistoryType;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;
import com.galaxy.memorit.historytype.infrastructure.persistence.mapper.HistoryTypeMapper;
import com.galaxy.memorit.historytype.infrastructure.persistence.repository.UserHistoryTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryTypeServiceImpl implements HistoryTypeService {
	private final UserHistoryTypeRepository userHistoryTypeRepository;
	private final HistoryTypeMapper historyTypeMapper;
	@Override
	public void registerHistoryType(String userId, HistoryTypeRegisterReqDTO dto) {
		UserHistoryType userHistoryType = UserHistoryType.builder()
			.userId(userId)
			.typeName(dto.getTypeName())
			.build();
		userHistoryTypeRepository.save(historyTypeMapper.toUserHistoryTypeEntity(userHistoryType));
	}
}
