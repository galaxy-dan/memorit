package com.galaxy.memorit.friend.application.service.implement;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.common.exception.NoSuchFriendException;
import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.friend.Infrastructure.persistence.mapper.FriendMapper;
import com.galaxy.memorit.friend.application.service.FriendService;
import com.galaxy.memorit.friend.dto.request.FriendMultiDeleteReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendRegisterFromAddressReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendUpdateReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;
import com.galaxy.memorit.friend.dto.response.FriendRankResDTO;
import com.galaxy.memorit.friend.dto.response.FriendRegisterResDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;
import com.galaxy.memorit.history.infrastructure.persistence.repository.HistoryRepository;
import com.galaxy.memorit.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
	private final FriendRepository friendRepository;
	private final FriendMapper friendMapper;
	private final UserRepository userRepository;
	private final HistoryRepository historyRepository;
	@Transactional
	@Override
	public FriendRegisterResDTO registerFriend(String userId, FriendRegisterReqDTO dto) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userId에 해당하는 회원이 존재하지 않을 때 예외 처리
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		UUID friendUUID = UUID.randomUUID();
		FriendEntity friendEntity = FriendEntity.builder()
			.friendId(friendUUID)
			.userId(userUUID)
			.name(dto.getName())
			.category(dto.getCategory())
			.build();

		//friendId에 새로운 UUID 생성하여 저장
		friendRepository.save(friendEntity);

		return new FriendRegisterResDTO(friendMapper.UUIDToHexString(friendUUID));
	}

	@Transactional
	@Override
	public void registerFriendsFromAddress(String userId, FriendRegisterFromAddressReqDTO dto) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		List<FriendEntity> list = dto.getNameList().stream()
				.map(name -> FriendEntity.builder()
					.friendId(UUID.randomUUID())
					.userId(userUUID)
					.name(name)
					.build())
				.collect(Collectors.toList());
		friendRepository.saveAll(list);
	}

	@Transactional(readOnly = true)
	@Override
	public FriendsListResDTO getFriendsList(String userId) {
		//byte[]를 UUID로 변경해서 db 조회
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		List<FriendEntity> entityList = friendRepository.findAllByUserId(userUUID);

		//db에서 얻은 리스트를 DTO에 맞게 변환
		List<FriendInfoDTO> infoList = entityList.stream()
			.map(friendMapper::toInfoDTO)
			.collect(Collectors.toList());

		return new FriendsListResDTO(1, infoList);
	}

	@Transactional(readOnly = true)
	@Override
	public FriendInfoDTO getFriendInfo(String userId, String friendId) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		FriendEntity entity = friendRepository.findByFriendIdAndUserId(friendMapper.stringToUUID(friendId), userUUID);
		if(entity == null){
			throw new NoSuchFriendException();
		}

		return friendMapper.toInfoDTO(entity);
	}

	@Transactional
	@Override
	public void updateFriendInfo(String userId, String friendId, FriendUpdateReqDTO dto) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		FriendEntity entity = friendRepository.findByFriendIdAndUserId(friendMapper.stringToUUID(friendId), userUUID);
		if(entity == null){
			throw new NoSuchFriendException();
		}

		entity.updateInfo(dto.getName(), dto.getCategory());
	}

	@Transactional
	@Override
	public void deleteFriendById(String userId, String friendId) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		UUID friendUUID = friendMapper.stringToUUID(friendId);
		FriendEntity entity = friendRepository.findByFriendIdAndUserId(friendUUID, userUUID);
		if(entity == null){
			throw new NoSuchFriendException();
		}

		friendRepository.delete(entity);
		historyRepository.deleteAllByFriendId(friendUUID);
	}

	@Transactional
	@Override
	public void deleteFriendsByList(String userId, FriendMultiDeleteReqDTO dto) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		//요청 받은 string list를 uuid list로 변환
		List<UUID> uuidList = dto.getIdList().stream()
				.map(friendMapper::stringToUUID)
				.collect(Collectors.toList());
		friendRepository.deleteAllByFriendsList(friendMapper.stringToUUID(userId), uuidList);
		historyRepository.deleteAllByFriendIds(uuidList);
	}

	@Transactional(readOnly = true)
	@Override
	public FriendRankResDTO getFriendsRank(String userId) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		Pageable pageable = PageRequest.of(0,1);

		List<FriendEntity> mostReceivedCountList = friendRepository.findFirstByReceivedCount(userUUID, pageable);
		List<FriendEntity> mostSentCountList = friendRepository.findFirstBySentCount(userUUID, pageable);
		List<FriendEntity> mostReceivedMoneyList = friendRepository.findFirstByReceivedMoney(userUUID, pageable);
		List<FriendEntity> mostSentMoneyList = friendRepository.findFirstBySentMoney(userUUID, pageable);

		FriendEntity mostReceivedCount, mostSentCount, mostReceivedMoney, mostSentMoney;
		mostReceivedCount = mostReceivedCountList.isEmpty() ? null : mostReceivedCountList.get(0);
		mostSentCount = mostSentCountList.isEmpty() ? null : mostSentCountList.get(0);
		mostReceivedMoney = mostReceivedMoneyList.isEmpty() ? null : mostReceivedMoneyList.get(0);
		mostSentMoney = mostSentMoneyList.isEmpty() ? null : mostSentMoneyList.get(0);

		return new FriendRankResDTO(
			friendMapper.toInfoDTO(mostReceivedCount),
			friendMapper.toInfoDTO(mostSentCount),
			friendMapper.toInfoDTO(mostReceivedMoney),
			friendMapper.toInfoDTO(mostSentMoney)
		);
	}

	@Transactional(readOnly = true)
	@Override
	public FriendsListResDTO searchFriends(String userId, FriendSearchReqDTO dto) {
		UUID userUUID = friendMapper.stringToUUID(userId);
		//userRepository.findById(userUUID).orElseThrow(NoSuchUserException::new);

		return friendRepository.findFriendsByDTO(userUUID, dto);
	}

}