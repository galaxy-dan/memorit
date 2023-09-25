package com.galaxy.memorit.common.authtemp.handler;

import com.galaxy.memorit.common.authtemp.config.AppProperties;
import com.galaxy.memorit.common.authtemp.entity.Provider;
import com.galaxy.memorit.common.authtemp.entity.RefreshToken;
import com.galaxy.memorit.common.authtemp.entity.Role;
import com.galaxy.memorit.common.authtemp.info.OAuth2UserInfo;
import com.galaxy.memorit.common.authtemp.info.OAuth2UserInfoFactory;
import com.galaxy.memorit.common.authtemp.repository.OAuth2CookieRepository;
import com.galaxy.memorit.common.authtemp.repository.RefreshTokenRepository;
import com.galaxy.memorit.common.authtemp.token.AuthToken;
import com.galaxy.memorit.common.authtemp.token.AuthTokenProvider;
import com.galaxy.memorit.common.authtemp.utils.CookieUtil;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import static com.galaxy.memorit.common.authtemp.repository.OAuth2CookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.galaxy.memorit.common.authtemp.repository.OAuth2CookieRepository.REFRESH_TOKEN;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2CookieRepository oAuth2CookieRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        long tokenExpiry = appProperties.getAuth().getTokenExpiry();
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
        int maxAge = (int) (refreshTokenExpiry / 1000);

        Optional<String> redirectUri = CookieUtil.get(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue);

        if (isUnauthorizedUri(redirectUri)) {
            throw new IllegalArgumentException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        OAuth2UserInfo userInfo = getOAuth2UserInfo(authentication);

        AuthToken accessToken = issueAccessToken(authentication, userInfo, tokenExpiry);
        AuthToken refreshToken = tokenProvider.createRefreshToken(
            appProperties.getAuth().getTokenSecret(), refreshTokenExpiry);
        persistRefreshToken(userInfo, accessToken);

        CookieUtil.renew(request, response, REFRESH_TOKEN, refreshToken.getToken(), maxAge);

        if (response.isCommitted()) {
            return;
        }

        clearAuthenticationAttributes(request, response);

        String targetUrl = determineTargetUrl(redirectUri, accessToken);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private OAuth2UserInfo getOAuth2UserInfo(Authentication authentication) {
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        Provider provider = Provider.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());
        OidcUser user = (OidcUser) authentication.getPrincipal();

        return OAuth2UserInfoFactory.getOAuth2UserInfo(provider, user.getAttributes());
    }

    private AuthToken issueAccessToken(Authentication authentication, OAuth2UserInfo userInfo, long tokenExpiry) {
        Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();
        Role role = Role.isOf(authorities, Role.ADMIN) ? Role.ADMIN : Role.USER;

        return tokenProvider.createAccessToken(userInfo.getProviderId(), role, tokenExpiry);
    }

    private void persistRefreshToken(OAuth2UserInfo userInfo, AuthToken refreshToken) {
        RefreshToken userRefreshToken = refreshTokenRepository.findByProviderId(userInfo.getProviderId());
        if (userRefreshToken == null) {
            userRefreshToken = RefreshToken.createRefreshToken(userInfo.getProviderId(), refreshToken.getToken());
            refreshTokenRepository.save(userRefreshToken);
        } else {
            userRefreshToken.updateRefreshToken(refreshToken.getToken());
        }
    }

    private String determineTargetUrl(Optional<String> redirectUri, AuthToken accessToken) {
        String uri = redirectUri.orElse(getDefaultTargetUrl());
        return UriComponentsBuilder.fromUriString(uri)
            .queryParam("token", accessToken.getToken())
            .build()
            .toUriString();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        oAuth2CookieRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isUnauthorizedUri(Optional<String> uri) {
        return uri.isPresent() && !isAuthorizedRedirectUri(uri.get());
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
            .stream()
            .anyMatch(isAuthorizedRedirectUri -> {
                URI authorizedURI = URI.create(isAuthorizedRedirectUri);

                return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                    && authorizedURI.getPort() == clientRedirectUri.getPort();
            });
    }
}
