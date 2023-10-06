package com.galaxy.memorit.common.api;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

//    private final static int SUCCESS = 200;
//    private final static int NOT_FOUND = 400;
//    private final static int FAILED = 500;
//    private final static String SUCCESS_MESSAGE = "SUCCESS";
//    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
//    private final static String FAILED_MESSAGE = "Server got an error.";
    private final static String INVALID_ACCESS_TOKEN = "Invalid access token.";
    private final static String INVALID_REFRESH_TOKEN = "Invalid refresh token.";
    private final static String NOT_EXPIRED_TOKEN_YET = "Not expired token yet.";

    private final static int OK = 200;
    private final static int BAD_REQUEST = 400;
    private final static int UNAUTHORIZED = 401;
    private final static int NOT_FOUND = 404;
    private final static int SERVER_INTERNAL_ERROR = 500;
    private final static String OK_MESSAGE = "OK";
    private final static String BAD_REQUEST_MESSAGE = "Bad Request";
    private final static String UNAUTHORIZED_MESSAGE = "Unauthorized";
    private final static String NOT_FOUND_MESSAGE = "Not Found";
    private final static String SERVER_INTERNAL_ERROR_MESSAGE = "Server Internal Error";

    private final ApiResponseHeader header;
    private final Map<String, T> body;

    public static <T> ApiResponse<T> ok(String name, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(name, body);

        return new ApiResponse(new ApiResponseHeader(OK, OK_MESSAGE), map);
    }

    public static <T> ApiResponse<T> badRequest(String header, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(header, body);

        return new ApiResponse(new ApiResponseHeader(BAD_REQUEST, BAD_REQUEST_MESSAGE), map);
    }

    public static <T> ApiResponse<T> unauthorized(String header, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(header, body);

        return new ApiResponse(new ApiResponseHeader(UNAUTHORIZED, UNAUTHORIZED_MESSAGE), map);
    }

    public static <T> ApiResponse<T> notFound(String header, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(header, body);

        return new ApiResponse(new ApiResponseHeader(NOT_FOUND, NOT_FOUND_MESSAGE), map);
    }

    public static <T> ApiResponse<T> serverInternalError(String header, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(header, body);

        return new ApiResponse(new ApiResponseHeader(SERVER_INTERNAL_ERROR, SERVER_INTERNAL_ERROR_MESSAGE), map);
    }

    public static <T> ApiResponse<T> invalidAccessToken() {
        return new ApiResponse(new ApiResponseHeader(SERVER_INTERNAL_ERROR, INVALID_ACCESS_TOKEN), null);
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return new ApiResponse(new ApiResponseHeader(SERVER_INTERNAL_ERROR, INVALID_REFRESH_TOKEN), null);
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return new ApiResponse(new ApiResponseHeader(SERVER_INTERNAL_ERROR, NOT_EXPIRED_TOKEN_YET), null);
    }


}
