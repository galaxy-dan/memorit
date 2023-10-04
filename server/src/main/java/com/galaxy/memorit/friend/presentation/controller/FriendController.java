package com.galaxy.memorit.friend.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.friend.application.service.FriendService;
import com.galaxy.memorit.friend.dto.request.FriendMultiDeleteReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendRegisterFromAddressReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendSearchReqDTO;
import com.galaxy.memorit.friend.dto.request.FriendUpdateReqDTO;
import com.galaxy.memorit.friend.dto.response.FriendInfoDTO;
import com.galaxy.memorit.friend.dto.response.FriendRankResDTO;
import com.galaxy.memorit.friend.dto.response.FriendRegisterFromAddressResDTO;
import com.galaxy.memorit.friend.dto.response.FriendRegisterResDTO;
import com.galaxy.memorit.friend.dto.response.FriendsListResDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
@CrossOrigin
public class FriendController {
	private final FriendService friendService;

	//수동으로 친구 등록
	@PostMapping
	public ResponseEntity<FriendRegisterResDTO> registerFriendManually(@RequestBody FriendRegisterReqDTO dto, Authentication authentication){
		//이게 찐
		//friendService.registerFriend(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";

		return ResponseEntity.status(HttpStatus.CREATED).body(friendService.registerFriend(uuid, dto));
	}

	@PostMapping("/select")
	public ResponseEntity<FriendRegisterFromAddressResDTO> registerFriendsFromAddress(@RequestBody FriendRegisterFromAddressReqDTO dto, Authentication authentication){
		//이게 찐
		//friendService.registerFriendsFromAddress(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		friendService.registerFriendsFromAddress(uuid, dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(
			new FriendRegisterFromAddressResDTO(201));
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
		//return ResponseEntity.ok(friendService.updateFriendInfo(authentication.getName(), friendId, dto));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		friendService.updateFriendInfo(uuid, friendId, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{friendId}")
	public ResponseEntity<Void> deleteFriendById(@PathVariable String friendId, Authentication authentication){
		//return ResponseEntity.ok(friendService.deleteFriendById(authentication.getName(), friendId));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		friendService.deleteFriendById(uuid, friendId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/multiple")
	public ResponseEntity<Void> deleteFriendsByList(@RequestBody FriendMultiDeleteReqDTO dto, Authentication authentication){
		//return ResponseEntity.ok(friendService.deleteFriendsByList(authentication.getName(), dto));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		friendService.deleteFriendsByList(uuid, dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/rank")
	public ResponseEntity<FriendRankResDTO> getFriendsRank(Authentication authentication){
		//return ResponseEntity.ok(friendService.getFriendsRank(authentication.getName()));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(friendService.getFriendsRank(uuid));
	}

	@GetMapping("/search")
	public ResponseEntity<FriendsListResDTO> searchFriends(FriendSearchReqDTO dto, Authentication authentication){
		//return ResponseEntity.ok(friendService.searchFriends(authentication.getName(), dto));
		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(friendService.searchFriends(uuid, dto));
	}
}
