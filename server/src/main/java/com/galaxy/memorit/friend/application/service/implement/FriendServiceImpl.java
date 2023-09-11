package com.galaxy.memorit.friend.application.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.friend.Infrastructure.persistence.repository.FriendRepository;
import com.galaxy.memorit.friend.Infrastructure.persistence.mapper.FriendMapper;
import com.galaxy.memorit.friend.application.service.FriendService;
import com.galaxy.memorit.friend.domain.entity.Friend;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
	private final FriendRepository friendRepository;
	private final FriendMapper friendMapper;
	@Transactional
	@Override
	public void registerFriend(byte[] userUUID, FriendRegisterReqDTO dto) {
		Friend friend = Friend.builder()
			.userId(userUUID)
			.name(dto.getName())
			.category(dto.getCategory())
			.build();

		//String 형태인 userId를 UUID로 변환하여 저장
		//friendId에 새로운 UUID 생성하여 저장
		friendRepository.save(friendMapper.createEntity(friend));
	}

}