package com.galaxy.memorit.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEntity {
    private long userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;
}
