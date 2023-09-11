package com.galaxy.memorit.friend.Infrastructure.persistence.mapper;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.domain.entity.Friend;

@Component
@Mapper(componentModel = "spring", imports = UUID.class)
public interface FriendMapper {
	//새로운 친구 생성시 사용
	@Mapping(target = "userId", expression = "java(byteArrayToUUID(friend.getUserId()))")
	@Mapping(target = "friendId", expression = "java(UUID.randomUUID())")
	FriendEntity createEntity(Friend friend);

	//수정 시 사용
	@Mapping(target = "userId", expression = "java(byteArrayToUUID(friend.getUserId()))")
	@Mapping(target = "friendId", expression = "java(byteArrayToUUID(friend.getUserId()))")
	FriendEntity toEntity(Friend friend);

	//byte[] 을 UUID로 변환하는 메서드
	default UUID byteArrayToUUID(byte[] byteArray){
		ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
		long mostSigBits = byteBuffer.getLong();
		long leastSigBits = byteBuffer.getLong();
		return new UUID(mostSigBits, leastSigBits);
	}

	//db에서 친구 데이터 받아서 변환 시 사용
	@Mapping(target = "userId", expression = "java(UUIDToByteArray(entity.getUserId()))")
	@Mapping(target = "friendId", expression = "java(UUIDToByteArray(entity.getUserId()))")
	Friend toDomain(FriendEntity entity);

	default byte[] UUIDToByteArray(UUID uuid) {
		ByteBuffer bArray = ByteBuffer.wrap(new byte[16]);
		bArray.putLong(uuid.getMostSignificantBits());
		bArray.putLong(uuid.getLeastSignificantBits());
		return bArray.array();
	}

}
