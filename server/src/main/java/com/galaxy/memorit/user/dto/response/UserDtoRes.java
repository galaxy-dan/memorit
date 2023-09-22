package com.galaxy.memorit.user.dto.response;

import com.galaxy.memorit.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoRes {

    private String providerId;
    private String userId;
    private String nickname;
    private int receivedCount;
    private int sentCount;
    private int receivedMoney;
    private int sentMoney;

    public static UserDtoRes from(User user) {
        return new UserDtoRes(
            user.getProviderId(),
            user.getUserId(),
            user.getNickname(),
            user.getReceivedCount(),
            user.getSentCount(),
            user.getReceivedMoney(),
            user.getSentMoney()
        );
    }
}
