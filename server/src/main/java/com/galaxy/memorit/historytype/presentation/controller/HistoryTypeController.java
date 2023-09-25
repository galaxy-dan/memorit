package com.galaxy.memorit.historytype.presentation.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.historytype.application.service.HistoryTypeService;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeRegisterReqDTO;
import com.galaxy.memorit.historytype.dto.request.HistoryTypeSearchReqDTO;
import com.galaxy.memorit.historytype.dto.response.HistoryTypeResDTO;

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
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/search")
	public ResponseEntity<HistoryTypeResDTO> searchHistoryType(HistoryTypeSearchReqDTO dto, Authentication authentication){
		//이게 찐
		//return ResponseEntity.ok(historyTypeService.searchHistoryType(authentication.getName(), dto));

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";

		return ResponseEntity.ok(historyTypeService.searchHistoryType(uuid, dto));
	}
}
