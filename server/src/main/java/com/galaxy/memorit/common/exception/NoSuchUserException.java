package com.galaxy.memorit.common.exception;

public class NoSuchUserException extends RuntimeException {
	public NoSuchUserException(){
		super("존재하지 않는 회원입니다. 재 로그인 필요!");
	}
}
