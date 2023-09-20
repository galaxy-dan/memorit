package com.galaxy.memorit.history.application.service.implement;

import org.springframework.stereotype.Service;

import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.domain.entity.History;
import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;
import com.galaxy.memorit.history.infrastructure.persistence.mapper.HistoryMapper;
import com.galaxy.memorit.history.infrastructure.persistence.repository.HistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
	private final HistoryRepository historyRepository;
	private final HistoryMapper historyMapper;
	@Override
	public void createHistory(String userId, HistoryCreateReqDTO dto) {
		History history= History.builder()
			.userId(userId)
			.friendId(dto.getFriendId())
			.date(dto.getDate())
			.type(dto.getType())
			.amount(dto.getAmount())
			.item(dto.getItem())
			.detail(dto.getDetail())
			.image(dto.getImage())
			.given(dto.isGiven())
			.build();
		historyRepository.save(historyMapper.toEntity(history));
	}
}
