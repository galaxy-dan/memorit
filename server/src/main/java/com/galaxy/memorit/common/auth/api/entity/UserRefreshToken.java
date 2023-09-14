package com.galaxy.memorit.common.auth.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_credential")
public class UserRefreshToken {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "email")
    private  String email;

    @Column(name = "refresh_token")
    private String refreshToken;

    public UserRefreshToken(long userId, String email, String refreshToken) {
        this.userId = userId;
        this.email = email;
        this.refreshToken = refreshToken;
    }
}
