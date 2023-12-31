package com.galaxy.memorit.history.application.service.implement;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.common.exception.AccessRefusedException;
import com.galaxy.memorit.common.exception.NoSuchFriendException;
import com.galaxy.memorit.common.exception.NoSuchHistoryException;
import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.dto.request.HistoryReqDTO;
import com.galaxy.memorit.history.dto.request.HistoryListReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.dto.response.HistoryRegisterResDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.entity.HistoryEntity;
import com.galaxy.memorit.history.infrastructure.persistence.mapper.HistoryMapper;
import com.galaxy.memorit.history.infrastructure.persistence.repository.HistoryRepository;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
	private final HistoryRepository historyRepository;
	private final HistoryMapper historyMapper;
	private final FriendRepository friendRepository;
	private final UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;
	@Transactional
	@Override
	public HistoryRegisterResDTO createHistory(String userId, HistoryReqDTO dto) {
		UUID userUUID = historyMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		FriendEntity friend = friendRepository.findByFriendIdAndUserId(historyMapper.stringToUUID(dto.getFriendId()), userUUID);
		if(friend == null){
			throw new NoSuchFriendException();
		}

		HistoryEntity history = HistoryEntity.builder()
			.userId(userUUID)
			.friendId(friend.getFriendId())
			.date(dto.getDate())
			.type(dto.getType())
			.amount(dto.getAmount())
			.item(dto.getItem())
			.detail(dto.getDetail())
			.image(dto.getImage())
			.given(dto.isGiven())
			.build();

		if(dto.isGiven()){
			friend.updateReceivedCount(friend.getReceivedCount()+1);
			if(dto.getAmount() != null) {
				friend.updateReceivedMoney(friend.getReceivedMoney() + dto.getAmount());
			}
		}else{
			friend.updateSentCount(friend.getSentCount()+1);
			if(dto.getAmount() != null) {
				friend.updateSentMoney(friend.getSentMoney() + dto.getAmount());
			}
		}

		if(friend.getRecentDate() == null || friend.getRecentDate().isBefore(dto.getDate())) {
			friend.updateRecentDate(dto.getDate());
		}
		friendRepository.save(friend);
		historyRepository.save(history);

		return new HistoryRegisterResDTO(String.valueOf(history.getId()));
	}

	@Transactional(readOnly = true)
	@Override
	public HistoryResDTO getHistory(String userId, long articleId) {
		UUID userUUID = historyMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		HistoryEntity historyEntity = historyRepository.findById(articleId).orElseThrow(NoSuchHistoryException::new);

		if(!historyEntity.getUserId().equals(userUUID)){
			throw new AccessRefusedException();
		}
		FriendEntity friendEntity = friendRepository.findById(historyEntity.getFriendId()).orElseThrow(NoSuchFriendException::new);


		return historyMapper.entityToResDTO(historyEntity, friendEntity.getName());
	}

	@Transactional(readOnly = true)
	@Override
	public HistoryListResDTO getTotalHistory(String userId, HistoryListReqDTO dto) {
		UUID userUUID = historyMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		String friendId = dto.getFriendId();
		UUID friendUUID = null;
		if(friendId != null) {
			friendUUID = historyMapper.stringToUUID(friendId);
			FriendEntity friend = friendRepository.findByFriendIdAndUserId(friendUUID, userUUID);
			if (friend == null) {
				throw new NoSuchFriendException();
			}
		}

		return historyRepository.getHistoriesByDTO(userUUID, dto, friendUUID);
	}

	@Override
	public void updateHistory(String userId, Long articleId,HistoryReqDTO dto) {
		UUID userUUID = historyMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);
		Long id = articleId;


		// HistoryEntity historyEntity = historyRepository.findById(article_id).orElseThrow(NoSuchHistoryException::new);
		HistoryEntity history = entityManager.find(HistoryEntity.class, id);

		if (history != null) {
			if(history.getUserId().equals(userUUID)) {
				// todo: 추후 friendId만 가져오도록 리펙토링
				FriendEntity friend = friendRepository.findByFriendIdAndUserId(historyMapper.stringToUUID(dto.getFriendId()), userUUID);
				if(friend == null){
					throw new NoSuchFriendException();
				}

				HistoryEntity modifiedHistory = HistoryEntity.builder()
						.id(id)
						.userId(userUUID)
						.friendId(friend.getFriendId())
						.date(dto.getDate())
						.type(dto.getType())
						.amount(dto.getAmount())
						.item(dto.getItem())
						.detail(dto.getDetail())
						.image(dto.getImage())
						.given(dto.isGiven())
						.build();
				historyRepository.save(modifiedHistory);
			}
		}
		else {
			throw new NoSuchHistoryException();
		}
	}

	@Override
	public void deleteHistory(String userId, Long articleId) {
		HistoryEntity history = historyRepository.findById(articleId).orElseThrow(NoSuchHistoryException::new);
		UUID userUUID = history.getUserId();
		UUID friendUUID = history.getFriendId();
		if(!userUUID.equals(historyMapper.stringToUUID(userId))){
			throw new AccessRefusedException();
		}

		FriendEntity friend = friendRepository.findByFriendIdAndUserId(friendUUID, userUUID);

		if(friend == null) {
			throw new NoSuchFriendException();
		}

		if(history.isGiven()){
			friend.updateReceivedCount(friend.getReceivedCount()-1);
			if(history.getAmount() != null) {
				friend.updateReceivedMoney(friend.getReceivedMoney() - history.getAmount());
			}
		}else{
			friend.updateSentCount(friend.getSentCount()-1);
			if(history.getAmount() != null) {
				friend.updateSentMoney(friend.getSentMoney() - history.getAmount());
			}
		}

		historyRepository.deleteById(articleId);

		LocalDate recentDate = historyRepository.findRecentDate(userUUID,friendUUID);
		friend.updateRecentDate(recentDate);
		friendRepository.save(friend);
	}
}
