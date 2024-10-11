package com.eaglecare.repository;

import com.eaglecare.entity.PayRollGenerationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRollGeneraionHistoryRepo extends JpaRepository<PayRollGenerationHistoryEntity, String> {
}
