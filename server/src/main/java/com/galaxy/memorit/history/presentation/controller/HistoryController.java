package com.galaxy.memorit.history.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.galaxy.memorit.history.application.service.HistoryService;
import com.galaxy.memorit.history.dto.request.HistoryReqDTO;
import com.galaxy.memorit.history.dto.request.HistoryListReqDTO;
import com.galaxy.memorit.history.dto.response.HistoryListResDTO;
import com.galaxy.memorit.history.dto.response.HistoryResDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@CrossOrigin
public class HistoryController {
	private final HistoryService historyService;

	@PostMapping
	public ResponseEntity<Void> createHistory(@RequestBody HistoryReqDTO dto){
		//이게 찐
		//historyService.createHistory(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		historyService.createHistory(uuid, dto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/detail/{articleId}")
	public ResponseEntity<HistoryResDTO> getHistory(@PathVariable long articleId){
		//이게 찐
		//historyService.getHistory(authentication.getName(), articleId);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(historyService.getHistory(uuid, articleId));
	}

	@GetMapping("/all")
	public ResponseEntity<HistoryListResDTO> getTotalHistory(HistoryListReqDTO dto){
		//이게 찐
		//historyService.getTotalHistory(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(historyService.getTotalHistory(uuid, dto));
	}

	@PutMapping("/detail/{articleId}")
	public ResponseEntity<Void> updateHistory(@PathVariable long articleId, @RequestBody HistoryReqDTO dto){
		//이게 찐
		//historyService.getTotalHistory(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";

		historyService.updateHistory(uuid, articleId ,dto);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/detail/{articleId}")
	public ResponseEntity<Void> deleteHistory(@PathVariable Long articleId){
		//이게 찐
		//historyService.getTotalHistory(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";

		historyService.deleteHistory(uuid, articleId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
