package com.galaxy.memorit.user.presentation.controller;

import com.galaxy.memorit.common.api.ApiResponse;
import com.galaxy.memorit.user.application.service.UserService;
import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.dto.request.UserDtoReq;
import com.galaxy.memorit.user.dto.response.UserDtoRes;
import com.galaxy.memorit.user.infrastructure.persistence.entity.UserJpaEntity;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<UserDtoRes> describeAuthenticatedUser() {
        UserJpaEntity user = userService.getAuthenticatedUser();
        return ApiResponse.ok("user", UserDtoRes.from(user));
    }

    @PutMapping
    public ApiResponse<UserDtoRes> registerUserInformation(@RequestBody UserDtoReq requestDto) {
        UserJpaEntity user = userService.registerUserInformation(requestDto);
        return ApiResponse.ok("user", UserDtoRes.from(user));
    }

    @PutMapping("/update-nickname")
    public ApiResponse<Boolean> updateNickname(@RequestBody UserDtoReq requestDto, String nickname) {
        userService.updateNickname(requestDto, nickname);
        return  ApiResponse.ok("result", true);
    }

    @PutMapping("/withdrawal")
    public ApiResponse<Boolean> updateWithdrawal(@RequestBody UserDtoReq requestDto) {
        userService.updateWithdrawal(requestDto);

        return ApiResponse.ok("result", true);
    }
}
