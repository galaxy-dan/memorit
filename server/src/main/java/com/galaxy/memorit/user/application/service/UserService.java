package com.galaxy.memorit.user.application.service;

import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.domain.repository.UserRepository;
import com.galaxy.memorit.user.dto.request.UserDtoReq;
import com.galaxy.memorit.user.infrastructure.persistence.entity.UserJpaEntity;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
//    private final UserJpaEntity user;    // 인텔리제이 버그??

    public UserJpaEntity getAuthenticatedUser() {
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserByProviderId(username);
    }

    private UserJpaEntity getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }

    private Optional<UserJpaEntity> getUserByUserId(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public UserJpaEntity registerUserInformation(UserDtoReq requestDto) {
        UserJpaEntity user = getUserByProviderId(requestDto.getProviderId());
        user.registerUserInformation(requestDto.getNickname());
        return user;
    }

    @Transactional
    public void updateNickname(UserDtoReq requestDto, String nickname) {
//        User user = getAuthenticatedUser();
        Optional<UserJpaEntity> userOption = getUserByUserId(requestDto.getUserId());

        if(!userOption.isPresent()) {
            throw new IllegalArgumentException();
        } else {
            UserJpaEntity user = userOption.get();
            user.updateNickname(nickname);
        }
    }

    @Transactional
    public void updateWithdrawal(UserDtoReq requestDto) {
        Optional<UserJpaEntity> userOption = getUserByUserId(requestDto.getUserId());

        if (!userOption.isPresent()) {
            throw new IllegalArgumentException();
        } else {
            UserJpaEntity user = userOption.get();
            user.updateWithdraw();
        }
    }


    // 닉네임 중복 체크
    // 동기화 기능과 유저간의 상호작용을 추가하면 필요할 듯?
//    public boolean isRegisteredNickname(String nickname) {
//        User user = userRepository.findByNickname(nickname);
//        return user != null;
//    }
}
