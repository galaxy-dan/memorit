package com.galaxy.memorit.user.dto.response;

import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.infrastructure.persistence.entity.UserJpaEntity;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoRes {

    private String providerId;
    private UUID userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;
    private boolean withdrawal;

    public static UserDtoRes from(UserJpaEntity user) {
        return new UserDtoRes(
            user.getProviderId(),
            user.getUserId(),
            user.getNickname(),
            user.getReceivedCount(),
            user.getSentCount(),
            user.getReceivedMoney(),
            user.getSentMoney(),
            user.isWithdrawal()
        );
    }
}
