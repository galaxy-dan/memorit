package com.galaxy.memorit.history.application.service.implement;

import org.springframework.stereotype.Service;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;
import com.galaxy.memorit.history.infrastructure.persistence.mapper.HistoryMapper;
import com.galaxy.memorit.history.infrastructure.persistence.repository.HistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
	private final HistoryRepository historyRepository;
	private final HistoryMapper historyMapper;
	private final FriendRepository friendRepository;
	@Override
	public void createHistory(String userId, HistoryCreateReqDTO dto) {
		FriendEntity friend = friendRepository.findByFriendIdAndUserId(historyMapper.stringToUUID(dto.getFriendId()), historyMapper.stringToUUID(userId));
		/*firend 존재하지 않으면 404

		 */

		HistoryEntity history = HistoryEntity.builder()
			.user(historyMapper.stringToUUID(userId))
			.friend(friend)
			.date(dto.getDate())
			.type(dto.getType())
			.amount(dto.getAmount())
			.item(dto.getItem())
			.detail(dto.getDetail())
			.image(dto.getImage())
			.given(dto.isGiven())
			.build();
		historyRepository.save(history);
	}

	@Override
	public HistoryResDTO getHistory(String userId, long articleId) {
		HistoryEntity historyEntity = historyRepository.findById(articleId).orElseThrow();
		/*if
		userId랑 history의 userId 일치하는지 확인 필요. 일치하지 않으면 401

		 */

		return historyMapper.entityToDTO(historyEntity);
	}
}
