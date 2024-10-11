package com.eaglecare.repository;

import com.eaglecare.entity.PaymentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsEntityRepo extends JpaRepository<PaymentsEntity,Long> {

    PaymentsEntity findByInvoiceId(Long invoiceId);
}
