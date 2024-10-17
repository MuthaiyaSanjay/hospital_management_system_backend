package com.eaglecare.repository;

import com.eaglecare.entity.PayRollEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayRollRepo extends JpaRepository<PayRollEntity, Long> {

    @Query(value = "SELECT p FROM PayRollEntity p WHERE p.user = :userId ORDER BY p.payment_date DESC")
    List<PayRollEntity> findMostRecentByUser(@Param("userId") long userId, Pageable pageable);



}
