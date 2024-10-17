package com.eaglecare.repository;

import com.eaglecare.entity.DeductionSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeductionSummaryRepo extends JpaRepository<DeductionSummaryEntity, Long> {
}
