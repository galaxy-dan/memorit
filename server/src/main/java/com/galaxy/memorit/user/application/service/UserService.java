package com.galaxy.memorit.user.application.service;

import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return getUserByProviderId(username);
    }

    public User getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }

    @Transactional
    public User registerUserInformation(UserInfoRequestDto requestDto) {
        User user = getUserByProviderId(requestDto.getProviderId());
        user.registerUserInformation(requestDto.getNickname(),
            MBTI.valueOf(requestDto.getMbti()),
            Gender.valueOf(requestDto.getGender()),
            requestDto.getBirth());
        return user;
    }

    @Transactional
    public void updateNickname(String nickname) {
        User user = getAuthenticatedUser();
        user.updateNickname(nickname);
    }

    @Transactional
    public void updateMbti(String mbti) {
        User user = getAuthenticatedUser();
        user.updateMbti(MBTI.valueOf(mbti));
    }

    @Transactional
    public void updateBirth(String birth) {
        User user = getAuthenticatedUser();
        user.updateBirth(birth);
    }

    @Transactional
    public void updateGender(String gender) {
        User user = getAuthenticatedUser();
        user.updateGender(Gender.valueOf(gender));
    }

    @Transactional
    public void withdrawUser() {
        User user = getAuthenticatedUser();
        user.updateWithdraw();
    }

    public UserUpdateResponseDto checkUpdateAvailability() {
        User user = getAuthenticatedUser();
        return UserUpdateResponseDto.create(
            user.isMbtiChangeable(),
            !user.isGenderChanged(),
            !user.isBirthChanged());
    }

    public boolean isRegisteredNickname(String nickname) {
        User user = userRepository.findByNickname(nickname);
        return user != null;
    }
}
