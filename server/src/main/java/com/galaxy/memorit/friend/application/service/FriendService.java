package com.galaxy.memorit.friend.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.friend.application.port.in.FriendRegisterUseCase;
import com.galaxy.memorit.friend.application.port.in.FriendRegisterCommand;
import com.galaxy.memorit.friend.application.port.out.FriendRegisterPort;
import com.galaxy.memorit.friend.domain.Friend;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService implements FriendRegisterUseCase {
	private final FriendRegisterPort friendRegisterPort;
	@Transactional
	@Override
	public void registerFriend(FriendRegisterCommand command) {
		Friend friend = Friend.builder()
			.userId(command.getUserId())
			.name(command.getName())
			.category(command.getCategory())
			.build();
		friendRegisterPort.register(friend);
	}
}
