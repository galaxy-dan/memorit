package com.galaxy.memorit.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseHeader {
    private int code;
    private String message;
}
