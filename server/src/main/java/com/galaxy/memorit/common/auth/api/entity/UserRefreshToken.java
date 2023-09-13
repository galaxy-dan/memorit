package com.galaxy.memorit.common.auth.api.entity;

import javax.persistence.Entity;
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
@Table(name = "userCredential")
public class UserRefreshToken {
    private long userId;
    private  String email;
    private long refreshToken;
}
