package com.eaglecare.repository;

import com.eaglecare.entity.LeaveAllocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveAllocationEntityRepo extends JpaRepository<LeaveAllocationEntity, Long> {
    Optional<LeaveAllocationEntity> findByUserId(Long userId);
}
