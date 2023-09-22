package com.galaxy.memorit.history.application.service.implement;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.common.exception.AccessRefusedException;
import com.galaxy.memorit.common.exception.NoSuchFriendException;
import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;
import com.galaxy.memorit.history.infrastructure.persistence.mapper.HistoryMapper;
import com.galaxy.memorit.history.infrastructure.persistence.repository.HistoryRepository;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
	private final HistoryRepository historyRepository;
	private final HistoryMapper historyMapper;
	private final FriendRepository friendRepository;
	private final UserRepository userRepository;
	@Transactional
	@Override
	public void createHistory(String userId, HistoryCreateReqDTO dto) {
		UUID userUUID = historyMapper.stringToUUID(userId);
		userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		FriendEntity friend = friendRepository.findByFriendIdAndUserId(historyMapper.stringToUUID(dto.getFriendId()), userUUID);
		if(friend == null){
			throw new NoSuchFriendException();
		}

		HistoryEntity history = HistoryEntity.builder()
			.user(userUUID)
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

	@Transactional(readOnly = true)
	@Override
	public HistoryResDTO getHistory(String userId, long articleId) {
		UUID userUUID = historyMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		HistoryEntity historyEntity = historyRepository.findById(articleId).orElseThrow();

		if(!historyEntity.getUser().equals(userUUID)){
			throw new AccessRefusedException();
		}

		return historyMapper.entityToDTO(historyEntity);
	}
}
