package com.eaglecare.repository;

import com.eaglecare.entity.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayEntityRepo extends JpaRepository<HolidayEntity, Long> {
}
