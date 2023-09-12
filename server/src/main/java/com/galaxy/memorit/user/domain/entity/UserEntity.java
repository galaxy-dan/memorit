package com.galaxy.memorit.user.domain.entity;

public class UserEntity {
    private long userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;

    public UserEntity(long userId, String nickname, int receivedCount, int sentCount,
        int receivedMoney,
        int sentMoney) {
        this.userId = userId;
        this.nickname = nickname;
        this.receivedCount = receivedCount;
        this.sentCount = sentCount;
        this.receivedMoney = receivedMoney;
        this.sentMoney = sentMoney;
    }
}
