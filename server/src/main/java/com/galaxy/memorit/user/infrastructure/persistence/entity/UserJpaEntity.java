package com.galaxy.memorit.user.infrastructure.persistence.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
}
