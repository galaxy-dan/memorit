package com.galaxy.memorit.category.presentation.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.memorit.category.application.service.CategoryService;
import com.galaxy.memorit.category.dto.request.CategoryRegisterReqDTO;
import com.galaxy.memorit.category.dto.request.CategorySearchReqDTO;
import com.galaxy.memorit.category.dto.response.CategoryResDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {
	private final CategoryService categoryService;
	@PostMapping
	public ResponseEntity<Void> registerCategory(@RequestBody CategoryRegisterReqDTO dto, Authentication authentication){
		//이게 찐
		//categoryService.registerCategory(authentication.getName(), dto);

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		categoryService.registerCategory(uuid, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<CategoryResDTO> searchCategory(CategorySearchReqDTO dto, Authentication authentication){
		//이게 찐
		//return ResponseEntity.ok(categoryService.searchCategory(authentication.getName()));

		//테스트용
		String uuid = "99d7f4dd55244c54a523032169193f40";
		return ResponseEntity.ok(categoryService.searchCategory(uuid, dto));
	}
}
