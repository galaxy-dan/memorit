package com.galaxy.memorit.user.dto.request;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDtoReq {
    private String providerId;
    private UUID userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;
    private boolean withdrawal;

    @Builder
    public UserDtoReq(UUID userId, String nickname, int receivedCount, int sentCount,
        int receivedMoney, int sentMoney, boolean withdrawal) {
        this.userId = userId;
        this.nickname = nickname;
        this.receivedCount = receivedCount;
        this.sentCount = sentCount;
        this.receivedMoney = receivedMoney;
        this.sentMoney = sentMoney;
        this.withdrawal = withdrawal;
    }

}
