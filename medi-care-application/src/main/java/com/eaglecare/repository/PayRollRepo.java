package com.medicare.repository;

import com.medicare.entity.PayRollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRollRepo extends JpaRepository<PayRollEntity, Long> {
}
