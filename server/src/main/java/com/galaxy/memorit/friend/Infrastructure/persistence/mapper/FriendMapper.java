package com.galaxy.memorit.friend.Infrastructure.persistence.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.galaxy.memorit.friend.Infrastructure.persistence.entity.FriendEntity;
import com.galaxy.memorit.friend.domain.entity.Friend;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;

@Component
@Mapper(componentModel = "spring", imports = UUID.class)
public interface FriendMapper {
	//새로운 친구 생성시 사용
	@Mapping(target = "userId", expression = "java(stringToUUID(friend.getUserId()))")
	@Mapping(target = "friendId", expression = "java(UUID.randomUUID())")
	FriendEntity createEntity(Friend friend);

	//수정 시 사용
	@Mapping(target = "userId", expression = "java(stringToUUID(friend.getUserId()))")
	@Mapping(target = "friendId", expression = "java(stringToUUID(friend.getFriendId()))")
	FriendEntity toEntity(Friend friend);

	//byte[] 을 UUID로 변환하는 메서드
	default UUID stringToUUID(String str){
		String formattedUUID = String.format(
			"%s-%s-%s-%s-%s",
			str.substring(0, 8),
			str.substring(8, 12),
			str.substring(12, 16),
			str.substring(16, 20),
			str.substring(20)
		);
		return UUID.fromString(formattedUUID);
	}

	//db에서 친구 데이터 받아서 변환 시 사용
	@Mapping(target = "userId", expression = "java(UUIDToHexString(entity.getUserId()))")
	@Mapping(target = "friendId", expression = "java(UUIDToHexString(entity.getFriendId()))")
	Friend toDomain(FriendEntity entity);


	@Mapping(target = "userId", expression = "java(UUIDToHexString(entity.getUserId()))")
	@Mapping(target = "friendId", expression = "java(UUIDToHexString(entity.getFriendId()))")
	FriendInfoDTO toInfoDTO(FriendEntity entity);

	default String UUIDToHexString(UUID uuid){
		return uuid.toString().replace("-", "");
	}

}
