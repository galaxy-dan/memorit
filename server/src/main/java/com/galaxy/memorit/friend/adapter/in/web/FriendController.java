package com.galaxy.memorit.friend.adapter.in.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.friend.adapter.in.web.dto.FriendRegisterReqDTO;
import com.galaxy.memorit.friend.application.port.in.FriendRegisterCommand;
import com.galaxy.memorit.friend.application.port.in.FriendRegisterUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
	private final FriendRegisterUseCase friendRegisterUseCase;

	//수동으로 친구 등록
	@PostMapping
	public ResponseEntity<Void> registerFriendManually(@RequestBody FriendRegisterReqDTO dto, Authentication authentication){
		//이게 찐
		//FriendRegisterCommand command = new FriendRegisterCommand(authentication.getName(), dto.getName(), dto.getCategory());

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		FriendRegisterCommand command = new FriendRegisterCommand(
			hexStringToByteArray(uuid), dto.getName(), dto.getCategory());

		friendRegisterUseCase.registerFriend(command);
		return ResponseEntity.created(URI.create("")).build();
	}

	//테스트용
	public static byte[] hexStringToByteArray(String hexString) {
		int len = hexString.length();
		byte[] byteArray = new byte[len / 2]; // 바이트 배열의 크기를 16진수 문자열 길이의 절반으로 설정

		for (int i = 0; i < len; i += 2) {
			// 16진수 문자열에서 두 개의 문자씩 읽어서 해당 값을 바이트로 변환하여 배열에 저장
			byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
				+ Character.digit(hexString.charAt(i + 1), 16));
		}

		return byteArray;
	}
}
