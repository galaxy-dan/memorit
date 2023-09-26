package com.galaxy.memorit.user.domain.repository;

import com.galaxy.memorit.user.infrastructure.persistence.entity.UserJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, UUID> {

    Optional<UserJpaEntity> findById(UUID userId);
    UserJpaEntity findByNickname(String nickname);
//    UserJpaEntity updateNickname(String nickname);
    UserJpaEntity findByProviderId(String providerId);
}
