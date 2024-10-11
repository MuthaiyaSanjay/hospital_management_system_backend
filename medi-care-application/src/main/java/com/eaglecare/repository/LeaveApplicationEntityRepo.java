package com.eaglecare.repository;

import com.eaglecare.entity.LeaveApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationEntityRepo extends JpaRepository<LeaveApplicationEntity, Long> {
}
