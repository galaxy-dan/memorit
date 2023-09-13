package com.galaxy.memorit.user.domain.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserEntity {
    private long userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;
}
