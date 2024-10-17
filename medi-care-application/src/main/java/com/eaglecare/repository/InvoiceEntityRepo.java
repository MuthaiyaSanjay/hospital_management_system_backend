package com.eaglecare.repository;

import com.eaglecare.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceEntityRepo extends JpaRepository<InvoiceEntity,Long> {
}
