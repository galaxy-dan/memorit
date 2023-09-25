package com.galaxy.memorit.common.authtemp.repository;

import com.galaxy.memorit.common.authtemp.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByProviderId(String providerId);
    RefreshToken findByProviderIdAndRefreshToken(String providerId, String refreshToken);
}
