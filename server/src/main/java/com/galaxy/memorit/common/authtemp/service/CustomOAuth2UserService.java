package com.galaxy.memorit.common.authtemp.service;

import com.galaxy.memorit.common.authtemp.entity.Provider;
import com.galaxy.memorit.common.authtemp.entity.Role;
import com.galaxy.memorit.common.authtemp.entity.UserPrincipal;
import com.galaxy.memorit.common.authtemp.info.OAuth2UserInfo;
import com.galaxy.memorit.common.authtemp.info.OAuth2UserInfoFactory;
import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.domain.repository.UserRepository;
import com.galaxy.memorit.user.infrastructure.persistence.entity.UserJpaEntity;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.createOrValidateUser(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User createOrValidateUser(OAuth2UserRequest userRequest, OAuth2User user) {
        Provider provider = Provider.valueOf(
            userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(provider,
            user.getAttributes());
        UserJpaEntity savedUser = userRepository.findByProviderId(userInfo.getProviderId());

        if (savedUser != null) {
            validateUser(savedUser, provider);
        } else {
            savedUser = createUser(userInfo, provider);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private void validateUser(UserJpaEntity user, Provider provider) {
        if (!user.isEqualProvider(provider)) {
            throw new OAuth2AuthenticationException(
                "Looks like you're signed up with " + provider +
                    " account. Please use your " + user.getProvider() + " account to login."
            );
        }

        if (user.isWithdrawal()) {
            throw new OAuth2AuthenticationException("이미 탈퇴한 회원입니다.");
        }
    }

    private UserJpaEntity createUser(OAuth2UserInfo userInfo, Provider provider) {

        UserJpaEntity user = UserJpaEntity.create(
            userInfo.getProviderId(),
            provider,
            userInfo.getUserId(),
            userInfo.getNickname(),
            userInfo.getReceivedCount(),
            userInfo.getSentCount(),
            userInfo.getReceivedMoney(),
            userInfo.getSentMoney()
        );

        return userRepository.save(user);
    }
}