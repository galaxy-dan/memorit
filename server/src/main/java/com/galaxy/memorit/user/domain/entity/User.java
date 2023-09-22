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
    private String providerId;
    private String userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;


    // 유저 계정 생성
    public static User create(String providerId, String userId, String nickname, int receivedCount, int sentCount, int receivedMoney, int sentMoney) {
        return User.builder()
            .providerId(providerId)
            .userId(userId)
            .nickname(nickname)
            .receivedCount(receivedCount)
            .sentCount(sentCount)
            .receivedMoney(receivedMoney)
            .sentMoney(sentMoney)
            .build();
    }

    public void registerUserInformation(String nickname) {
        this.nickname = nickname;
    }

    // 닉네임 수정
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    // 회원 정보 조회


    // 회원 탈퇴



    // 히스토리(마음) 받았을 때 receivedCount, receivedMoney 업데이트
    // 히스토리(마음) 보낼 때 sentCount, sentMoney 업데이트
    // history 도메인에서 처리하면 user 도메인은 신경쓰지 않아도 되는건가? 아님 user 도메인에서 작성해야 되나?

}
