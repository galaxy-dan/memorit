package com.galaxy.memorit.common.authtemp.service;

import com.galaxy.memorit.common.api.ApiResponse;
import com.galaxy.memorit.common.config.security.AppProperties;
import com.galaxy.memorit.common.authtemp.entity.RefreshToken;
import com.galaxy.memorit.common.authtemp.exception.InvalidTokenException;
import com.galaxy.memorit.common.authtemp.exception.NotExpiredTokenException;
import com.galaxy.memorit.common.authtemp.repository.RefreshTokenRepository;
import com.galaxy.memorit.common.authtemp.token.AuthToken;
import com.galaxy.memorit.common.authtemp.token.AuthTokenProvider;
import com.galaxy.memorit.common.authtemp.token.TokenValidator;
import com.galaxy.memorit.common.authtemp.utils.HeaderUtil;
import com.galaxy.memorit.common.authtemp.utils.CookieUtil;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final static long DAY_MILLI_SEC = 86400000;
    private final static String REFRESH_TOKEN = "refresh_token";

    public ApiResponse reissueRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = HeaderUtil.getAccessToken(request);

        TokenValidator v1 = validateAccessTokenBeforeReissue(accessToken);
        if (v1 != null) {
            return v1.getResult();
        }

        String refreshToken = CookieUtil.get(request, REFRESH_TOKEN)
            .map(Cookie::getValue)
            .orElse(null);
        String providerId = tokenProvider.getExpiredTokenProviderId(accessToken);

        TokenValidator v2 = validateRefreshToken(refreshToken);
        if (v2 != null) {
            return v2.getResult();
        }

        RefreshToken userRefreshToken = refreshTokenRepository.findByProviderIdAndRefreshToken(
            providerId, refreshToken);

        if (userRefreshToken == null) {
            return ApiResponse.invalidRefreshToken();
        }

        AuthToken newAccessToken = tokenProvider.createAccessToken(
            providerId, tokenProvider.getExpiredTokenRole(accessToken), appProperties.getAuth().getTokenExpiry());

        renewRefreshToken(request, response, refreshToken, userRefreshToken);
        return ApiResponse.ok("token", newAccessToken.getToken());
    }

    private TokenValidator validateAccessTokenBeforeReissue(String token) {
        try {
            tokenProvider.validateAccessTokenBeforeReissue(token);
        } catch (InvalidTokenException | NotExpiredTokenException e) {
            return new TokenValidator(e, false);
        }
        return null;
    }

    private TokenValidator validateRefreshToken(String token) {
        try {
            tokenProvider.validateRefreshToken(token);
        } catch (InvalidTokenException e) {
            return new TokenValidator(e, true);
        }
        return null;
    }

    private void renewRefreshToken(HttpServletRequest request, HttpServletResponse response,
        String refreshToken, RefreshToken savedRefreshToken) {

        long validTime = tokenProvider.getExpiration(refreshToken).getTime() - new Date().getTime();

        if (validTime < DAY_MILLI_SEC * 3) {
            return;
        }

        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
        int maxAge = (int) (refreshTokenExpiry / 1000);

        AuthToken newRefreshToken = tokenProvider.createRefreshToken(
            appProperties.getAuth().getTokenSecret(), refreshTokenExpiry);
        savedRefreshToken.updateRefreshToken(newRefreshToken.getToken());
        CookieUtil.renew(request, response, REFRESH_TOKEN, newRefreshToken.getToken(), maxAge);
    }
}
