package com.galaxy.memorit.historytype.application.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.historytype.application.service.HistoryTypeService;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeSearchReqDTO;
import com.galaxy.memorit.historytype.dto.response.HistoryTypeResDTO;
import com.galaxy.memorit.historytype.infrastructure.persistence.entity.HistoryTypeEntity;
import com.galaxy.memorit.historytype.infrastructure.persistence.mapper.HistoryTypeMapper;
import com.galaxy.memorit.historytype.infrastructure.persistence.repository.HistoryTypeRepository;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryTypeServiceImpl implements HistoryTypeService {
	private final HistoryTypeRepository historyTypeRepository;
	private final HistoryTypeMapper historyTypeMapper;
	private final UserRepository userRepository;
	@Transactional
	@Override
	public void registerHistoryType(String userId, HistoryTypeRegisterReqDTO dto) {
		UUID userUUID = historyTypeMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		HistoryTypeEntity historyTypeEntity = HistoryTypeEntity.builder()
			.userId(userUUID)
			.typeName(dto.getTypeName())
			.build();
		historyTypeRepository.save(historyTypeEntity);
	}

	@Transactional(readOnly = true)
	@Override
	public HistoryTypeResDTO searchHistoryType(String userId, HistoryTypeSearchReqDTO dto) {
		UUID userUUID = historyTypeMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		List<String> typeList = historyTypeRepository.searchHistoryTypes(userUUID, dto);
		return new HistoryTypeResDTO(typeList);
	}
}
