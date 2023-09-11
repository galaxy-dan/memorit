package com.galaxy.memorit.friend.presentation.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.friend.application.service.FriendService;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendUpdateReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
	private final FriendService friendService;

	//수동으로 친구 등록
	@PostMapping
	public ResponseEntity<Void> registerFriendManually(@RequestBody FriendRegisterReqDTO dto, Authentication authentication){
		//이게 찐
		//friendService.registerFriend(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		friendService.registerFriend(uuid, dto);

		return ResponseEntity.created(URI.create("")).build();
	}

	@GetMapping
	public ResponseEntity<FriendsListResDTO> getFriendsList(Authentication authentication){
		//return ResponseEntity.ok(friendService.getFriendsList(authentication.getName()));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(friendService.getFriendsList(uuid));
	}

	@GetMapping("/{friendId}")
	public ResponseEntity<FriendInfoDTO> getFriendInfo(@PathVariable String friendId, Authentication authentication){
		//return ResponseEntity.ok(friendService.getFriendInfo(authentication.getName(), friendId));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(friendService.getFriendInfo(uuid, friendId));
	}

	@PatchMapping("/{friendId}")
	public ResponseEntity<Void> updateFriendInfo(@PathVariable String friendId, @RequestBody FriendUpdateReqDTO dto, Authentication authentication){
		//return ResponseEntity.ok(friendService.updateFriendInfo(authentication.getName(), friendId));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		friendService.updateFriendInfo(uuid, friendId, dto);
		return ResponseEntity.ok().build();
	}
}
