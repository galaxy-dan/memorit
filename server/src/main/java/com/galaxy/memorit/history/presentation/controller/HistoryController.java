package com.galaxy.memorit.history.presentation.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.dto.request.HistoryCreateReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;

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

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{articleId}")
	public ResponseEntity<HistoryResDTO> getHistory(@PathVariable long articleId){
		//이게 찐
		//historyService.getHistory(authentication.getName(), articleId);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(historyService.getHistory(uuid, articleId));
	}

	@GetMapping("/all/{friendId}")
	public ResponseEntity<HistoryListResDTO> getTotalHistory(@PathVariable String friendId){
		//이게 찐
		//historyService.getTotalHistory(authentication.getName(), articleId);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(historyService.getTotalHistory(uuid, friendId));
	}
}
