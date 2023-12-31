package com.galaxy.memorit.common.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.galaxy.memorit.common.exception.AccessRefusedException;
import com.galaxy.memorit.common.exception.NoSuchFriendException;
import com.galaxy.memorit.common.exception.NoSuchHistoryException;
import com.galaxy.memorit.common.exception.NoSuchUserException;
import com.galaxy.memorit.common.exception.dto.ExceptionResDTO;

@ControllerAdvice
public class ExceptionAdvice {
	//존재하지 않는 user
	@ExceptionHandler(NoSuchUserException.class)
	public ResponseEntity<ExceptionResDTO> NoSuchUserException(NoSuchUserException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResDTO(e.getMessage()));
	}

	//존재하지 않는 friend
	@ExceptionHandler(NoSuchFriendException.class)
	public ResponseEntity<ExceptionResDTO> NoSuchFriendException(NoSuchFriendException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResDTO(e.getMessage()));
	}

	@ExceptionHandler(AccessRefusedException.class)
	public ResponseEntity<ExceptionResDTO> AccessRefusedException(AccessRefusedException e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResDTO(e.getMessage()));
	}

	@ExceptionHandler(NoSuchHistoryException.class)
	public ResponseEntity<ExceptionResDTO> NoSuchHistoryException(NoSuchHistoryException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResDTO(e.getMessage()));
	}
}
