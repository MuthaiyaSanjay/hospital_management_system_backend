package com.eaglecare.repository;

import com.eaglecare.entity.LeaveApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApprovalRepo extends JpaRepository<LeaveApprovalEntity, Long> {
}
