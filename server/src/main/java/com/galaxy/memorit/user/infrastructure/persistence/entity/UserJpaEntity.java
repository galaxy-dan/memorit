package com.galaxy.memorit.user.infrastructure.persistence.entity;

import com.galaxy.memorit.common.authtemp.entity.Provider;
import com.galaxy.memorit.user.domain.entity.User;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserJpaEntity {

    @Id
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;
    @Column(name = "provider_id")
    private String providerId;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    @Column(name = "nickname", length = 16, nullable = false)
    private String nickname;
    @Column(name = "received_count")
    private int receivedCount;
    @Column(name = "sent_count")
    private int sentCount;
    @Column(name = "received_money")
    private int receivedMoney;
    @Column(name = "sent_money")
    private int sentMoney;
    @Column(name = "withdrawal")
    private boolean withdrawal;



    // 유저 계정 생성
    public static UserJpaEntity create(String providerId, Provider provider, UUID userId, String nickname, int receivedCount, int sentCount, int receivedMoney, int sentMoney, boolean withdrawal) {
        return UserJpaEntity.builder()
            .providerId(providerId)
            .provider(provider)
            .userId(userId)
            .nickname(nickname)
            .receivedCount(receivedCount)
            .sentCount(sentCount)
            .receivedMoney(receivedMoney)
            .sentMoney(sentMoney)
            .withdrawal(withdrawal)
            .build();
    }

    public void registerUserInformation(String nickname) {
        this.nickname = nickname;
    }

    // 닉네임 수정
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    // 회원탈퇴
    public void updateWithdraw() {
        this.withdrawal = true;
        this.nickname = null;
    }

    public boolean isEqualProvider(Provider provider) {
        return this.provider.equals(provider);
    }

    // 회원 정보 조회


    // 회원 탈퇴



    // 히스토리(마음) 받았을 때 receivedCount, receivedMoney 업데이트
    // 히스토리(마음) 보낼 때 sentCount, sentMoney 업데이트
    // history 도메인에서 처리하면 user 도메인은 신경쓰지 않아도 되는건가? 아님 user 도메인에서 작성해야 되나?

}
