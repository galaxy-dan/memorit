package com.galaxy.memorit.common.authtemp.info.impl;

import com.galaxy.memorit.common.authtemp.info.OAuth2UserInfo;
import java.util.Map;
import java.util.UUID;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    private Map<String, Object> kakaoAccount;
    private Map<String, Object> profile;


    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        this.kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.profile = (Map<String, Object>) this.kakaoAccount.get("profile");
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public UUID getUserId() {
        return null;
    }

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public int getReceivedCount() {
        return 0;
    }

    @Override
    public int getSentCount() {
        return 0;
    }

    @Override
    public int getReceivedMoney() {
        return 0;
    }

    @Override
    public int getSentMoney() {
        return 0;
    }

//    public abstract String getUserId();
//    public abstract String getNickname();
//    public abstract int getReceivedCount();
//    public abstract int getSentCount();
//    public abstract int getReceivedMoney();
//    public abstract int getSentMoney();

//    @Override
//    public String getEmail() {
//        return (String) kakaoAccount.get("email");
//    }

//    @Override
//    public String getImageUrl() {
//        if (profile == null) {
//            return null;
//        }
//
//        return (String) profile.get("profile_image_url");
//    }

//    @Override
//    public String getBirth() {
//        if (kakaoAccount == null) {
//            return "0";
//        }
//
//        if (!kakaoAccount.containsKey("birthyear")) {
//            return "0";
//        }
//
//        return (String) kakaoAccount.get("birthyear");
//    }

}
