package com.galaxy.memorit.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    // 히스토리(마음) 받았을 때 receivedCount, receivedMoney 업데이트
    // 히스토리(마음) 보낼 때 sentCount, sentMoney 업데이트
    // history 도메인에서 처리하면 user 도메인은 신경쓰지 않아도 되는건가? 아님 user 도메인에서 작성해야 되나?

}
