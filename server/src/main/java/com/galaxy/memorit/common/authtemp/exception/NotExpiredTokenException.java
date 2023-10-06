package com.galaxy.memorit.common.authtemp.exception;

public class NotExpiredTokenException extends RuntimeException {
    public NotExpiredTokenException() {
        super("아직 만료되지 않은 토큰입니다.");
    }
}
