package com.galaxy.memorit.history.presentation.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {
	private final HistoryService historyService;

	@PostMapping
	public ResponseEntity<Void> createHistory(@RequestBody HistoryCreateReqDTO dto){
		//이게 찐
		//historyService.createHistory(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		historyService.createHistory(uuid, dto);

		return ResponseEntity.created(URI.create("")).build();
	}
}
