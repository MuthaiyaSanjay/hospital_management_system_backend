package com.eaglecare.repository;

import com.eaglecare.entity.UserBasicInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBasicInfoRepo extends JpaRepository<UserBasicInfoEntity, Long> {

    @Query("SELECT u FROM UserBasicInfoEntity u WHERE u.role.name = :roleName")
    Page<UserBasicInfoEntity> findByRoleName(String roleName, Pageable pageable);

}
