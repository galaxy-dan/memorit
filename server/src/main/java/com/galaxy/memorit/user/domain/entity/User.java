package com.galaxy.memorit.user.domain.entity;

import com.galaxy.memorit.common.authtemp.entity.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String providerId;
    private Provider provider;
    private String userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;
    private boolean withdrawal;


}
