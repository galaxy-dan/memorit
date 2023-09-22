package com.galaxy.memorit.user.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDtoReq {
    private String providerId;
    private String userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;

    @Builder
    public UserDtoReq(String userId, String nickname, int receivedCount, int sentCount,
        int receivedMoney, int sentMoney) {
        this.userId = userId;
        this.nickname = nickname;
        this.receivedCount = receivedCount;
        this.sentCount = sentCount;
        this.receivedMoney = receivedMoney;
        this.sentMoney = sentMoney;
    }

}
