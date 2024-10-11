package com.eaglecare.repository;

import com.eaglecare.entity.ProfessionalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalInformationRepo extends JpaRepository<ProfessionalInformationEntity, Long> {
}
