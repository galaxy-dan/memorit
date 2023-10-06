package com.galaxy.memorit.common.exception;

public class NoSuchFriendException extends RuntimeException{
	public NoSuchFriendException() {
		super("존재하지 않는 친구입니다.");
	}
}
