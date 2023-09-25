package com.galaxy.memorit.common.authtemp.info;

import com.galaxy.memorit.common.authtemp.entity.Provider;
import com.galaxy.memorit.common.authtemp.info.impl.KakaoOAuth2UserInfo;
import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(Provider provider, Map<String, Object> attributes) {
        switch (provider) {
//            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
//            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider");
        }
    }
}
