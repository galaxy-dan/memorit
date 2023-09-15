package com.galaxy.memorit.common.auth.api.repository;

import com.galaxy.memorit.common.auth.api.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    UserRefreshToken findByUserId(long userId);
    UserRefreshToken findByUserIdAndRefreshToken(long userId, String refreshToken);
}
