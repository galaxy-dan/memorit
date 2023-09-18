package com.galaxy.memorit.user.domain.repository;

import com.galaxy.memorit.user.domain.entity.User;
import com.galaxy.memorit.user.infrastructure.persistence.entity.UserJpaEntity;
import com.galaxy.memorit.user.infrastructure.persistence.repositoryImpl.UserRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserJpaEntity, Long> {
    User findByUserId(String userId);
    User findByNickname(String nickname);
}
