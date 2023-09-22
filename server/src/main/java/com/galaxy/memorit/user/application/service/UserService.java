package com.galaxy.memorit.user.application.service;

import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.domain.repository.UserRepository;
import com.galaxy.memorit.user.dto.request.UserDtoReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final User user;    // 인텔리제이 버그??

    public User getAuthenticatedUser() {
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserByProviderId(username);
    }

    private User getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);

    }

    @Transactional
    public User registerUserInformation(UserDtoReq requestDto) {
        User user = getUserByProviderId(requestDto.getProviderId());
        user.registerUserInformation(requestDto.getNickname());
        return user;
    }

    @Transactional
    public void updateNickname(String nickname) {
//        User user = getAuthenticatedUser();
        user.updateNickname(nickname);
    }

    // 닉네임 중복 체크
    // 동기화 기능과 유저간의 상호작용을 추가하면 필요할 듯?
//    public boolean isRegisteredNickname(String nickname) {
//        User user = userRepository.findByNickname(nickname);
//        return user != null;
//    }
}
