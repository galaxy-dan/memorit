package com.galaxy.memorit.historytype.application.service.implement;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.historytype.application.service.HistoryTypeService;
import com.galaxy.memorit.historytype.domain.entity.UserHistoryType;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;
import com.galaxy.memorit.historytype.dto.response.HistoryTypeResDTO;
import com.galaxy.memorit.historytype.infrastructure.persistence.entity.BaseHistoryTypeEntity;
import com.galaxy.memorit.historytype.infrastructure.persistence.entity.UserHistoryTypeEntity;
import com.galaxy.memorit.historytype.infrastructure.persistence.mapper.HistoryTypeMapper;
import com.galaxy.memorit.historytype.infrastructure.persistence.repository.BaseHistoryTypeRepository;
import com.galaxy.memorit.historytype.infrastructure.persistence.repository.UserHistoryTypeRepository;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryTypeServiceImpl implements HistoryTypeService {
	private final UserHistoryTypeRepository userHistoryTypeRepository;
	private final BaseHistoryTypeRepository baseHistoryTypeRepository;
	private final HistoryTypeMapper historyTypeMapper;
	private final UserRepository userRepository;
	@Transactional
	@Override
	public void registerHistoryType(String userId, HistoryTypeRegisterReqDTO dto) {
		UUID userUUID = historyTypeMapper.stringToUUID(userId);
		userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		UserHistoryTypeEntity userHistoryTypeEntity = UserHistoryTypeEntity.builder()
			.userId(userUUID)
			.typeName(dto.getTypeName())
			.build();
		userHistoryTypeRepository.save(userHistoryTypeEntity);
	}

	@Transactional(readOnly = true)
	@Override
	public HistoryTypeResDTO getHistoryType(String userId) {
		UUID userUUID = historyTypeMapper.stringToUUID(userId);
		userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		List<String> typeList = baseHistoryTypeRepository.findAll()
			.stream()
			.map(BaseHistoryTypeEntity::getTypeName)
			.collect(Collectors.toList());
		typeList.addAll(userHistoryTypeRepository.findAllByUserId(userUUID)
			.stream()
			.map(UserHistoryTypeEntity::getTypeName)
			.collect(Collectors.toList()));
		return new HistoryTypeResDTO(typeList);
	}
}
