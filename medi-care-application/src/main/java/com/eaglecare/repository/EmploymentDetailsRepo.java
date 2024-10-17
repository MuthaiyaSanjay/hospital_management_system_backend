package com.eaglecare.repository;

import com.eaglecare.entity.EmploymentDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDetailsRepo extends JpaRepository<EmploymentDetailsEntity, Long> {
}
