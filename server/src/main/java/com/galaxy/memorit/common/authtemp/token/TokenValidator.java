package com.galaxy.memorit.common.authtemp.token;

import com.galaxy.memorit.common.api.ApiResponse;
import com.galaxy.memorit.common.authtemp.exception.InvalidTokenException;
import com.galaxy.memorit.common.authtemp.exception.NotExpiredTokenException;
import lombok.Getter;

@Getter
public class TokenValidator {
    private ApiResponse result;
    private Exception exception;

    public TokenValidator(Exception e, boolean isRefreshToken) {
        this.exception = e;

        if (e.getClass().equals(InvalidTokenException.class)) {
            this.result = isRefreshToken ? ApiResponse.invalidRefreshToken() : ApiResponse.invalidAccessToken();
        } else if (e.getClass().equals(NotExpiredTokenException.class)) {
            this.result = ApiResponse.notExpiredTokenYet();
        } else {
            this.result = null;
        }
    }
}
