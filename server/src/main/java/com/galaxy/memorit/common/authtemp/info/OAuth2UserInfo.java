package com.galaxy.memorit.common.authtemp.info;

import java.util.Map;
import java.util.UUID;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getProviderId();
    public abstract UUID getUserId();
    public abstract String getNickname();
    public abstract int getReceivedCount();
    public abstract int getSentCount();
    public abstract int getReceivedMoney();
    public abstract int getSentMoney();

}
