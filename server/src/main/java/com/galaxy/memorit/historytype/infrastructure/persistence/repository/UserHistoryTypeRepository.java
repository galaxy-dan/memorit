package com.galaxy.memorit.historytype.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.memorit.historytype.infrastructure.persistence.entity.UserHistoryTypeEntity;
@Repository
public interface UserHistoryTypeRepository extends JpaRepository<UserHistoryTypeEntity, Integer> {

}
