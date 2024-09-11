package com.medicare.repository;

import com.medicare.entity.UserBasicInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBasicInfoRepo extends JpaRepository<UserBasicInfoEntity, Long> {
}
