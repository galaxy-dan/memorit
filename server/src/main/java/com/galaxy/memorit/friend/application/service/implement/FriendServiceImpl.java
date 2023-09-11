package com.galaxy.memorit.friend.application.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendKey;
import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.friend.Infrastructure.persistence.mapper.FriendMapper;
import com.galaxy.memorit.friend.application.service.FriendService;
import com.galaxy.memorit.friend.domain.entity.Friend;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
	private final FriendRepository friendRepository;
	private final FriendMapper friendMapper;
	@Transactional
	@Override
	public void registerFriend(String userId, FriendRegisterReqDTO dto) {
		Friend friend = Friend.builder()
			.userId(userId)
			.name(dto.getName())
			.category(dto.getCategory())
			.build();

		//String 형태인 userId를 UUID로 변환하여 저장
		//friendId에 새로운 UUID 생성하여 저장
		friendRepository.save(friendMapper.createEntity(friend));
	}

	@Override
	public FriendsListResDTO getFriendsList(String userId) {
		//byte[]를 UUID로 변경해서 db 조회
		List<FriendEntity> entityList = friendRepository.findAllByUserId(friendMapper.stringToUUID(userId));

		//db에서 얻은 리스트를 DTO에 맞게 변환
		List<FriendInfoDTO> infoList = entityList.stream()
			.map(friendMapper::toInfoDTO)
			.collect(Collectors.toList());

		return new FriendsListResDTO(infoList);
	}

	@Override
	public FriendInfoDTO getFriendInfo(String userId, String friendId) {
		FriendEntity entity = friendRepository.findById(new FriendKey(friendMapper.stringToUUID(userId), friendMapper.stringToUUID(friendId)))
			.orElseThrow();
		return friendMapper.toInfoDTO(entity);
	}

}