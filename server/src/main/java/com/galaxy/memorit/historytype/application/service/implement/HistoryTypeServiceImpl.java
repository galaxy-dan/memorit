package com.galaxy.memorit.historytype.application.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.galaxy.memorit.historytype.application.service.HistoryTypeService;
import com.galaxy.memorit.historytype.domain.entity.UserHistoryType;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;
import com.galaxy.memorit.historytype.dto.response.HistoryTypeResDTO;
import com.galaxy.memorit.historytype.infrastructure.persistence.entity.BaseHistoryTypeEntity;
import com.galaxy.memorit.historytype.infrastructure.persistence.entity.UserHistoryTypeEntity;
import com.galaxy.memorit.historytype.infrastructure.persistence.mapper.HistoryTypeMapper;
import com.galaxy.memorit.historytype.infrastructure.persistence.repository.BaseHistoryTypeRepository;
import com.galaxy.memorit.historytype.infrastructure.persistence.repository.UserHistoryTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryTypeServiceImpl implements HistoryTypeService {
	private final UserHistoryTypeRepository userHistoryTypeRepository;
	private final BaseHistoryTypeRepository baseHistoryTypeRepository;
	private final HistoryTypeMapper historyTypeMapper;
	@Override
	public void registerHistoryType(String userId, HistoryTypeRegisterReqDTO dto) {
		UserHistoryType userHistoryType = UserHistoryType.builder()
			.userId(userId)
			.typeName(dto.getTypeName())
			.build();
		userHistoryTypeRepository.save(historyTypeMapper.toUserHistoryTypeEntity(userHistoryType));
	}

	@Override
	public HistoryTypeResDTO getHistoryType(String userId) {
		List<String> typeList = baseHistoryTypeRepository.findAll()
			.stream()
			.map(BaseHistoryTypeEntity::getTypeName)
			.collect(Collectors.toList());
		typeList.addAll(userHistoryTypeRepository.findAllByUserId(historyTypeMapper.stringToUUID(userId))
			.stream()
			.map(UserHistoryTypeEntity::getTypeName)
			.collect(Collectors.toList()));
		return new HistoryTypeResDTO(typeList);
	}
}
