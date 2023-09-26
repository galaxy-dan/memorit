package com.galaxy.memorit.common.exception;

public class NoSuchHistoryException extends RuntimeException{
	public NoSuchHistoryException(){
		super("기억 상실!");
	}
}
