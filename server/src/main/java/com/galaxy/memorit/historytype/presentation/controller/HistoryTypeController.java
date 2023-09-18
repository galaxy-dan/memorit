package com.galaxy.memorit.historytype.presentation.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.historytype.application.service.HistoryTypeService;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class HistoryTypeController {
	private final HistoryTypeService historyTypeService;
	@PostMapping
	public ResponseEntity<Void> registerHistoryType(@RequestBody HistoryTypeRegisterReqDTO dto, Authentication authentication){
		//이게 찐
		//historyTypeService.registerHistoryType(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		historyTypeService.registerHistoryType(uuid, dto);
		return ResponseEntity.created(URI.create("")).build();
	}
}
