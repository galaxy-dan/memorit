package com.galaxy.memorit.friend.adapter.out.persistence;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.galaxy.memorit.friend.adapter.out.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.adapter.out.persistence.entity.FriendRepository;
import com.galaxy.memorit.friend.application.port.out.FriendRegisterPort;
import com.galaxy.memorit.friend.domain.Friend;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FriendPersistenceAdapter implements FriendRegisterPort {
	private final FriendRepository friendRepository;
	@Transactional
	@Override
	public void register(Friend friend) {
		//String 형태인 userId를 UUID로 변환하여 저장
		//friendId에 새로운 UUID 생성하여 저장
		UUID userId = userIdByteArrayToUUID(friend.getUserId());
		//UUID userId = userIdStringToUUID(friend.getUserId());
		System.out.println(userId);
		friendRepository.save(FriendEntity.builder()
			.userId(userId)
			.friendId(UUID.randomUUID())
			.name(friend.getName())
			.category(friend.getCategory())
			.build());
	}

	//문자열을 UUID로 변환하는 메서드
	private UUID userIdStringToUUID(String userIdString) {
		System.out.println(userIdString);
		return new UUID(
			Long.parseUnsignedLong(userIdString.substring(0, 16), 16),
			Long.parseUnsignedLong(userIdString.substring(16), 16)
		);
	}

	//byte[] 을 UUID로 변환하는 메서드
	private UUID userIdByteArrayToUUID(byte[] byteArray){
		ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
		long mostSigBits = byteBuffer.getLong();
		long leastSigBits = byteBuffer.getLong();
		return new UUID(mostSigBits, leastSigBits);
	}
}
