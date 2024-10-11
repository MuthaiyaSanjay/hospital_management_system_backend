package com.eaglecare.repository;

import com.eaglecare.entity.CheckInCheckOutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckInCheckOutRepo extends JpaRepository<CheckInCheckOutEntity,Long> {

    Optional<CheckInCheckOutEntity> findLatestCheckInByUserIdAndDate(Long userId, LocalDate date);

    @Query("SELECT c FROM CheckInCheckOutEntity c WHERE c.userId = :userId AND c.date = :date")
    Optional<CheckInCheckOutEntity> findByUserIdAndDate(Long userId, LocalDate date);
}
