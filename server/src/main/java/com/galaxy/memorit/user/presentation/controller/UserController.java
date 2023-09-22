package com.galaxy.memorit.user.presentation.controller;

import com.galaxy.memorit.common.api.ApiResponse;
import com.galaxy.memorit.user.application.service.UserService;
import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.dto.request.UserDtoReq;
import com.galaxy.memorit.user.dto.response.UserDtoRes;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<UserDtoRes> describeAuthenticatedUser() {
        User user = userService.getAuthenticatedUser();
        return ApiResponse.ok("user", UserDtoRes.from(user));
    }

    @PutMapping
    public ApiResponse<UserDtoRes> registerUserInformation(@RequestBody UserDtoReq requestDto) {
        User user = userService.registerUserInformation(requestDto);
        return ApiResponse.ok("user", UserDtoRes.from(user));
    }

    @PutMapping("/update-nickname")
    public ApiResponse<Boolean> updateNickname(@RequestBody String nickname) {
        userService.updateNickname(nickname);

        return  ApiResponse.ok("result", true);
    }
}
